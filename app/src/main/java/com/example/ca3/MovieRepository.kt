package com.example.ca3

class MovieRepository(private val dao: MovieDao) {

    val movies = dao.getAllMovies()

    suspend fun addMovie(movie: Movie) {
        dao.insertMovie(movie)
    }

    suspend fun deleteMovie(movie: Movie) {
        dao.deleteMovie(movie)
    }
}