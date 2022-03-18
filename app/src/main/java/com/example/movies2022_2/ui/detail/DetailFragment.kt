package com.example.movies2022_2.ui.detail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.movies2022_2.R
import com.example.movies2022_2.databinding.FragmentDetailBinding
import com.example.movies2022_2.server.model.Film
import com.squareup.picasso.Picasso

class DetailFragment : Fragment() {

    private lateinit var detailBinding: FragmentDetailBinding
    private lateinit var detailViewModel: DetailViewModel
    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        detailBinding = FragmentDetailBinding.inflate(inflater,container,false)
        detailViewModel = ViewModelProvider(this)[DetailViewModel::class.java]
        return detailBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movie: Film = args.film
        with(detailBinding){
            titleMovieTextView.text=movie.title
            releaseDateTextView.text=movie.releaseDate
            averageScoreTextView.text=movie.voteAverage.toString()
            Picasso.get().load("https://image.tmdb.org/t/p/w500"+movie.posterPath).into(posterImageView)
            summaryTextView.text=movie.overview
        }
    }

}