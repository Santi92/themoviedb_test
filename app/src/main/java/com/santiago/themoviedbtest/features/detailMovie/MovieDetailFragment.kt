package com.santiago.themoviedbtest.features.detailMovie

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.postDelayed
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.transition.TransitionInflater
import com.santiago.themoviedbtest.R
import com.santiago.themoviedbtest.data.local.entity.MovieEntity
import com.santiago.themoviedbtest.databinding.FragmentMovieDetailBinding
import com.squareup.picasso.Picasso
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_movie_detail.*
import javax.inject.Inject


class MovieDetailFragment : Fragment() {

    private lateinit var binding: FragmentMovieDetailBinding
    private var handler = Handler(Looper.getMainLooper())


    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: MovieDetailViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_detail, container, false)
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(R.transition.move)

        arguments?.run {
            val params = MovieDetailFragmentArgs.fromBundle(this)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                binding.image.transitionName = params.movieId.toString()
            }
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MovieDetailViewModel::class.java)

        arguments?.run {
            val params = MovieDetailFragmentArgs.fromBundle(this)
            postponeEnterTransition()
            loadMovie(params.movieId)
        }

    }

    private fun loadMovie(movieId: Long){
        viewModel.loadMovie(movieId)
        viewModel.movie.observe(viewLifecycleOwner, Observer {
            it.data?.run {

                cardView.visibility = View.VISIBLE

                setupDetailMovie(movie = this)

                startPostponedEnterTransition()
                handler.postDelayed(1000) {
                    startPostponedEnterTransition()
                }

            }
        })
    }

    private fun setupDetailMovie(movie: MovieEntity){
        binding.txtDescription.text = movie.description
        binding.txTitle.text = movie.title
        binding.txLanguage.text = movie.originalLanguage
        binding.txReleaseDate.text = movie.releaseDate

        Picasso.get().load(movie.getMoviePath())
                .placeholder(R.drawable.placeholder_movie)
                .into(binding.image)
    }


}
