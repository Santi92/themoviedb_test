package com.santiago.themoviedbtest.features.movies

import android.app.Activity
import android.os.Build

import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.santiago.themoviedbtest.R
import com.santiago.themoviedbtest.data.local.entity.*
import com.santiago.themoviedbtest.databinding.ListItemMovieBinding
import com.squareup.picasso.Picasso


class MoviesListAdapter(private val activity: Activity,
                        private val movieClickCallback: ((MovieEntity,ImageView) -> Unit)?)
    : RecyclerView.Adapter<MoviesListAdapter.CustomViewHolder>() {

    private var movies: MutableList<MovieEntity> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = ListItemMovieBinding.inflate(layoutInflater, parent, false)
        return CustomViewHolder(itemBinding)
    }

    fun setItems(movies: List<MovieEntity>) {
        val startPosition = this.movies.size
        this.movies.addAll(movies)
        notifyItemRangeChanged(startPosition, movies.size)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    private fun getItem(position: Int): MovieEntity {
        return movies[position]
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    inner class CustomViewHolder(private val binding: ListItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {

            val displayMetrics = DisplayMetrics()
            activity.windowManager.defaultDisplay.getMetrics(displayMetrics)
            val width = displayMetrics.widthPixels

            itemView.layoutParams = RecyclerView.LayoutParams(
                (width * 0.85f).toInt(),
                RecyclerView.LayoutParams.WRAP_CONTENT
            )
        }

        fun bindTo(movie: MovieEntity) {
            Picasso.get().load(movie.getMoviePath())
                .placeholder(R.drawable.placeholder_movie)
                .into(binding.image)

            binding.circularProgress.maxProgress = 100.0
            binding.circularProgress.setCurrentProgress(movie.getVoteAveragePercentage())


            binding.circularProgress.progressColor = ContextCompat.getColor(binding.root.context,
                getColorByRaking(movie.getTypeRaking()))


            binding.title.text = movie.title

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                binding.image.transitionName = movie.id.toString()
            }

            binding.cardFilm.setOnClickListener {
                movieClickCallback?.invoke(movie,binding.image)
            }
        }
    }

    private fun getColorByRaking(rakingMovie: TypeRakingMovie): Int = when(rakingMovie) {
        Regular -> R.color.colorRakingRegular
        Success -> R.color.colorRakingSuccess
        Low -> R.color.colorRakingLow
    }
}