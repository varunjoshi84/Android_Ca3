package com.example.ca3

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun WatchlistScreen(viewModel: MovieViewModel) {

    val movies by viewModel.movies.collectAsState()

    var movieName by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        OutlinedTextField(
            value = movieName,
            onValueChange = { movieName = it },
            label = { Text("Enter Movie Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                if (movieName.isNotBlank()) {
                    viewModel.addMovie(movieName)
                    movieName = ""
                }
            }
        ) {
            Text("Add Movie")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {

            items(movies, key = { it.id }) { movie ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Text(
                            text = movie.name,
                            modifier = Modifier.weight(1f)
                        )

                        Button(
                            onClick = {
                                viewModel.addMovie(movie.name)
                            }
                        ) {
                            Text("Add")
                        }

                        Spacer(modifier = Modifier.width(5.dp))

                        Button(
                            onClick = {
                                viewModel.deleteMovie(movie)
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.error
                            )
                        ) {
                            Text("Delete")
                        }
                    }
                }
            }
        }
    }
}
