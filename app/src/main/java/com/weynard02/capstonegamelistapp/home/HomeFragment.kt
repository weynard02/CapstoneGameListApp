package com.weynard02.capstonegamelistapp.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.weynard02.capstonegamelistapp.R
import com.weynard02.capstonegamelistapp.databinding.FragmentHomeBinding
import com.weynard02.capstonegamelistapp.detail.DetailActivity
import com.weynard02.core.data.Resource
import com.weynard02.core.ui.GameAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModel()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            setupAdapter()
        }

    }

    private fun setupAdapter() {
        val gameAdapter = GameAdapter()
        setupRecyclerView()
        binding.rvGame.adapter = gameAdapter

        gameAdapter.onItemClick = { selectedData ->
            val intent = Intent(activity, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, selectedData)
            startActivity(intent)
        }

        viewModel.games.observe(viewLifecycleOwner) { game ->
            if (game != null) {
                when (game) {
                    is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                    is Resource.Success -> {
                        gameAdapter.setData(null)
                        binding.progressBar.visibility = View.GONE
                        gameAdapter.setData(game.data)
                    }
                    is Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
                        binding.viewError.root.visibility = View.VISIBLE
                        binding.viewError.tvError.text = game.message ?: getString(R.string.something_wrong)
                    }
                }
            }
        }

        with(binding) {
            searchView.setupWithSearchBar(searchBar)
            searchView
                .editText
                .setOnEditorActionListener { _, _, _ ->
                    searchBar.setText(searchView.text)
                    searchView.hide()

                    val query: String = searchView.text.toString()

                    viewModel.searchGame(query).observe(viewLifecycleOwner) { game ->
                        if (game != null) {
                            when (game) {
                                is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                                is Resource.Success -> {
                                    gameAdapter.setData(null)
                                    binding.progressBar.visibility = View.GONE
                                    gameAdapter.setData(game.data)
                                }
                                is Resource.Error -> {
                                    binding.progressBar.visibility = View.GONE
                                    binding.viewError.root.visibility = View.VISIBLE
                                    binding.viewError.tvError.text = game.message ?: getString(R.string.something_wrong)
                                }
                            }
                        }
                    }
                    false
                }

        }



    }

    private fun setupRecyclerView() {
        binding.rvGame.layoutManager = LinearLayoutManager(context)
        binding.rvGame.setHasFixedSize(true)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}