package com.example.project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso


class MovieDetailsActivity : AppCompatActivity() {
    private lateinit var movie: Movie
    private  lateinit var titleTextView: TextView
    private lateinit var yearTextView: TextView
    private lateinit var posterImageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)
        titleTextView = findViewById(R.id.textView2)
        yearTextView = findViewById(R.id.textView3)
        posterImageView = findViewById(R.id.imageView)

        val movie = intent.getSerializableExtra("movie") as Movie
        titleTextView.text = movie.title
        yearTextView.text = movie.year
        Picasso.get().load(movie.poster).into(posterImageView)

    }
}