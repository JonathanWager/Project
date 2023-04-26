package com.example.project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.database.*

class FavoriteListActivity : AppCompatActivity() {
    private lateinit var db : DatabaseReference
    private lateinit var favoritesListView: ListView
    private lateinit var favoritesList: ArrayList<String>
    private lateinit var favoriteCounter: TextView
    private lateinit var viewModelFactory: CountFactory
    private lateinit var viewModel: CounterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite_list)
        favoritesListView = findViewById(R.id.listView)
        favoriteCounter = findViewById(R.id.textView6)
        favoritesList = ArrayList()
        db = FirebaseDatabase
            .getInstance ("https://project-database-movie-default-rtdb.europe-west1.firebasedatabase.app/" )
            .getReference ("favorites")

        db.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                favoritesList.clear()
                for (childSnapshot in snapshot.children) {
                    val favoriteData = childSnapshot.getValue() as HashMap<*, *>
                    val favoriteTitle = favoriteData["name"] as? String
                    if (favoriteTitle != null) {
                        favoritesList.add("$favoriteTitle")
                    }
                }
                viewModelFactory = CountFactory(favoritesList.size)
                viewModel = ViewModelProvider(this@FavoriteListActivity, viewModelFactory).get(CounterViewModel::class.java)
                favoriteCounter.text = viewModel.getCount().toString()
                val adapter = ArrayAdapter(this@FavoriteListActivity, android.R.layout.simple_list_item_1, favoritesList)
                favoritesListView.adapter = adapter
                Log.d("FavoriteListActivity", "onDataChange: $favoritesList")
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@FavoriteListActivity, "Error: ${error.message}", Toast.LENGTH_LONG).show()
            }
        })
    }
}