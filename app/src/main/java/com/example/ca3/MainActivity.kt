package com.example.ca3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import kotlin.jvm.java

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = Room.databaseBuilder(
            applicationContext,
            MovieDatabase::class.java,
            "movie_database"
        ).build()

        val repository = MovieRepository(db.movieDao())

        val factory = MovieViewModelFactory(repository)

        val viewModel = ViewModelProvider(
            this,
            factory
        )[MovieViewModel::class.java]

        setContent {
            MaterialTheme {
                WatchlistScreen(viewModel)
            }
        }
    }
}


