package com.weynard02.favorite

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.weynard02.capstonegamelistapp.detail.DetailActivity
import com.weynard02.core.ui.GameAdapter
import com.weynard02.favorite.databinding.ActivityFavoriteBinding
import com.weynard02.favorite.di.favoriteModule
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding

    private val viewModel: FavoriteViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadKoinModules(favoriteModule)
        supportActionBar?.title = "Favorite Games"

        setupView()
    }

    private fun setupView() {
        val gameAdapter = GameAdapter()
        setupRecyclerView()
        binding.rvGame.adapter = gameAdapter

        gameAdapter.onItemClick = { selectedData ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, selectedData)
            startActivity(intent)
        }

        viewModel.favoriteGame.observe(this) { dataGame ->
            gameAdapter.setData(dataGame)
            binding.viewEmpty.visibility = if (dataGame.isNotEmpty()) View.GONE else View.VISIBLE
        }


    }

    private fun setupRecyclerView() {
        binding.rvGame.layoutManager = LinearLayoutManager(binding.rvGame.context)
        binding.rvGame.setHasFixedSize(true)
    }
}