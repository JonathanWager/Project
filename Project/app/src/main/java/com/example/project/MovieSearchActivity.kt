package com.example.project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.SearchView
import android.widget.Toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MovieSearchActivity : AppCompatActivity() {
    private lateinit var searchView: SearchView
    private lateinit var listView: ListView
    private lateinit var adapter: ArrayAdapter<String>
    private var movieList: MutableList<Movie> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_search)
        searchView = findViewById(R.id.searchView2)
        listView = findViewById(R.id.listView)

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1)
        listView.adapter = adapter

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    fetchMovie(it)
                }
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }
    /*
    private fun fetchMovie(title: String) {
        val movie = fetchMovieData(title)
        if (movie != null) {
            movieList.add(movie)
            adapter.add("${movie.title} (${movie.year})")
        } else {
            Toast.makeText(this, "Movie not found", Toast.LENGTH_SHORT).show()
        }
    }
     */
    private fun fetchMovie(title: String) {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val movie = withContext(Dispatchers.IO) {
                    fetchMovieData(title)
                }
                if (movie != null) {
                    movieList.add(movie)
                    adapter.add("${movie.title} (${movie.year})")
                } else {
                    Toast.makeText(this@MovieSearchActivity, "Movie not found", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(this@MovieSearchActivity, "Error fetching movie: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

}