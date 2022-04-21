package com.example.myapplication.ui.activities

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.data.models.Movies
import com.example.myapplication.data.models.Results
import com.example.myapplication.databinding.AcMoviesBinding
import com.example.myapplication.di.ViewModelFactory
import com.example.myapplication.ui.MoviesAdapter
import com.example.myapplication.viewmodels.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MoviesActivity : AppCompatActivity() {

    private lateinit var errorObserver: Observer<String>
    private lateinit var moviesObserver: Observer<List<Results>>
    private lateinit var adapter: MoviesAdapter
    private lateinit var binding: AcMoviesBinding
    private val viewModel: MoviesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AcMoviesBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setUpMoviesUI()
        setUpObservers()
        subscribe()
        viewModel.fetchMovies()
    }

    private fun setUpObservers() {
        moviesObserver = Observer<List<Results>> { adapter.setData(it) }
        errorObserver = Observer<String> { it?.let { showError(it) } }
    }

    private fun showError(error: String?) = Toast.makeText(this,error,Toast.LENGTH_LONG).show()

    override fun onDestroy() {
        unsubscribe()
        super.onDestroy()
    }

    private fun unsubscribe() {
        viewModel.movies.removeObserver(moviesObserver)
        viewModel.error.removeObserver(errorObserver)
    }

    private fun setUpMoviesUI() {
        adapter = MoviesAdapter()
        with(binding) {
            moviesRecyclerView.apply {
                adapter = this@MoviesActivity.adapter
                layoutManager = GridLayoutManager(this@MoviesActivity, 3)
            }
        }
    }

    private fun subscribe() {
        with(viewModel) {
            movies.observe(this@MoviesActivity, moviesObserver)
            error.observe(this@MoviesActivity, errorObserver)
        }
    }
}