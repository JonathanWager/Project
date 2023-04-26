package com.example.project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.squareup.picasso.Picasso


class MovieDetailsActivity : AppCompatActivity() {
    private lateinit var movie: Movie
    private  lateinit var titleTextView: TextView
    private lateinit var yearTextView: TextView
    private lateinit var descriptionTextView: TextView
    private lateinit var posterImageView: ImageView
    private lateinit var myButton: Button
    private lateinit var db : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)
        titleTextView = findViewById(R.id.textView2)
        yearTextView = findViewById(R.id.textView3)
        posterImageView = findViewById(R.id.imageView)
        descriptionTextView = findViewById(R.id.textView4)
        myButton = findViewById(R.id.button)
        db = FirebaseDatabase
            .getInstance ("https://project-database-movie-default-rtdb.europe-west1.firebasedatabase.app/" )
            .getReference ("favorites")

        movie = intent.getSerializableExtra("movie") as Movie
        titleTextView.text = movie.title
        yearTextView.text = movie.year
        descriptionTextView.text = movie.plot
        Picasso.get().load(movie.poster).into(posterImageView)

        myButton.setOnClickListener {
            db.push().setValue(
                Favorites(movie.title)
            ).addOnSuccessListener {
                Toast.makeText(this, "Added to favorites" , Toast.LENGTH_LONG ).show()
            }.addOnFailureListener {
                Toast.makeText(this, "FAILURE", Toast.LENGTH_LONG ).show()
            }
        }
    }
}