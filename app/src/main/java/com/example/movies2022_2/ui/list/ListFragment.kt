package com.example.movies2022_2.ui.list

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movies2022_2.databinding.FragmentListBinding
import com.example.movies2022_2.server.model.Film

class ListFragment : Fragment() {

    private lateinit var listViewModel: ListViewModel
    private lateinit var listBinding: FragmentListBinding
    private lateinit var moviesAdapter: MoviesAdapter
    private val moviesList: ArrayList<Film> =ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        listViewModel = ViewModelProvider(this)[ListViewModel::class.java]
        listBinding= FragmentListBinding.inflate(inflater,container, false)
        return listBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        moviesAdapter= MoviesAdapter(moviesList, onItemClicked = { onMovieItemClicked(it)})

        listBinding.moviesRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@ListFragment.requireContext())
            adapter = moviesAdapter
            setHasFixedSize(false)
        }

        listViewModel.getMovies()

        listViewModel.loadMoviesDone.observe(viewLifecycleOwner){
            result ->
            onLoadMoviesDoneSuscribe(result)
        }
    }

    private fun onLoadMoviesDoneSuscribe(moviesList: ArrayList<Film>?) {
        moviesList?.let { moviesAdapter.appendItems(it) }
    }

    private fun onMovieItemClicked(movie: Film) {
        findNavController().navigate(ListFragmentDirections.actionListFragmentToDetailFragment(movie))
    }

}