package com.weynard02.capstonegamelistapp.detail

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.weynard02.capstonegamelistapp.R
import com.weynard02.capstonegamelistapp.databinding.ActivityDetailBinding
import com.weynard02.core.domain.model.Game
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    private val viewModel: DetailViewModel by viewModel()
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val detailGame = intent.getParcelableExtra<Game>(EXTRA_DATA)
        showDetailGame(detailGame)
    }

    private fun showDetailGame(detailGame: Game?) {
        detailGame?.let {
            with(binding) {
                tvName.text = detailGame.name
                Glide.with(this@DetailActivity)
                    .load(detailGame.backgroundImage)
                    .into(ivBackground)
                tvRating.text = getString(R.string.rating_data, detailGame.rating.toString())
                tvPlaytime.text =
                    getString(R.string.playtime_data, detailGame.playtime.toString())
                tvReleased.text =
                    getString(R.string.released_date, detailGame.released)

                var isFavorite = detailGame.isFavorite
                setFavorite(isFavorite)
                fab.setOnClickListener {
                    isFavorite = !isFavorite
                    Toast.makeText(this@DetailActivity,
                        if (isFavorite)
                            "Favorite game added!"
                        else
                            "Favorite game removed!",
                        Toast.LENGTH_SHORT).show()
                    viewModel.setFavoriteGame(detailGame, isFavorite)
                    setFavorite(isFavorite)
                }
            }

        }
    }

    private fun setFavorite(state: Boolean) {
        binding.fab.setImageDrawable(
            ContextCompat.getDrawable(
                this,
                if (state)
                    R.drawable.baseline_favorite_24
                else
                    R.drawable.baseline_favorite_border_24
            )
        )
    }
}