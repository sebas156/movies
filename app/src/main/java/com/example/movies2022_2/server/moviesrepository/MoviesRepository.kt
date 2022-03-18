package com.example.movies2022_2.server.moviesrepository

import com.example.movies2022_2.server.MovieDB
import com.example.movies2022_2.server.model.Film

class MoviesRepository {


    private val apikey = "287d752c94ffe860bd1eb6c8a6c91457"

    suspend fun getMovies() = MovieDB.retrofit.getTopRated(apikey)
}