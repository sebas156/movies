package com.example.movies2022_2.ui.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movies2022_2.server.model.Film
import com.example.movies2022_2.server.moviesrepository.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ListViewModel : ViewModel() {

    private val moviesRepository= MoviesRepository()

    private val loadMovies : MutableLiveData<ArrayList<Film>> = MutableLiveData()
    val loadMoviesDone: MutableLiveData<ArrayList<Film>> = loadMovies

    fun getMovies() {
        GlobalScope.launch(Dispatchers.IO){
            val moviesListServer = moviesRepository.getMovies()
            loadMovies.postValue(moviesListServer.filmsList as ArrayList<Film>)
        }
    }

}