package com.example.movies2022_2.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movies2022_2.R
import com.example.movies2022_2.databinding.CardViewItemMovieBinding
import com.example.movies2022_2.server.model.Film
import com.squareup.picasso.Picasso

class MoviesAdapter (
    private val moviesList: ArrayList<Film>,
    private val onItemClicked: (Film) -> Unit
): RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>(){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MoviesAdapter.MovieViewHolder {
        //Le indico cual es el layout donde voy a pintar
        val itemView= LayoutInflater.from(parent.context).inflate(R.layout.card_view_item_movie,parent,false)
        return MovieViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MoviesAdapter.MovieViewHolder, position: Int) {
        val movie = moviesList[position]
        holder.bindMovie(movie)
        holder.itemView.setOnClickListener {
            onItemClicked(moviesList[position])
        }
    }

    override fun getItemCount(): Int=moviesList.size

    fun appendItems(newList: ArrayList<Film>){
        moviesList.clear()
        moviesList.addAll(newList)
        notifyDataSetChanged()
    }

    class MovieViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        private val binding = CardViewItemMovieBinding.bind(itemView)
        fun bindMovie(movie: Film){
                with(binding){
                    titleMovieTextView.text=movie.title
                    releaseDateTextView.text=movie.releaseDate
                    averageScoreTextView.text=movie.voteAverage.toString()
                    Picasso.get().load("https://image.tmdb.org/t/p/w500"+movie.posterPath).into(posterImageView)
                }
        }
    }

}