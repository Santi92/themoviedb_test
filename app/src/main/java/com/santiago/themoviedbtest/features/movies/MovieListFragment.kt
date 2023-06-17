package com.santiago.themoviedbtest.features.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.transition.TransitionInflater
import com.santiago.themoviedbtest.R
import com.santiago.themoviedbtest.data.local.entity.MovieEntity
import com.santiago.themoviedbtest.databinding.FragmentMovieListBinding
import com.santiago.themoviedbtest.utils.FragmentNavigatorExtras
import com.santiago.themoviedbtest.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MovieListFragment: Fragment(){

    companion object {
        var binding: FragmentMovieListBinding? =  null
    }

    private val moviesListViewModel: MovieListViewModel by  viewModels()



    private  var moviesListAdapter  by autoCleared<MoviesListAdapter>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (binding == null){
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_list, container, false)
            sharedElementReturnTransition = TransitionInflater.from(requireContext()).inflateTransition(R.transition.move)
        }

        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if ( binding!!.moviesList.adapter == null){
            initialiseView()
            initialiseViewModel()
        }
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }

    private fun initialiseView() {

        moviesListAdapter = MoviesListAdapter(requireActivity()){ movie, image ->
            val extras = FragmentNavigatorExtras(
                image to movie.id.toString()
            )
            NavHostFragment
                .findNavController(this)
                .navigate(MovieListFragmentDirections.showMovieDetail(movie.id),extras)
        }

        binding!!.moviesList.layoutManager = GridLayoutManager(requireContext(), 2)
        binding!!.moviesList.adapter = moviesListAdapter

    }


    private fun initialiseViewModel() {
        moviesListViewModel.reload(true)
        moviesListViewModel.listMovie.observe(viewLifecycleOwner, Observer { resource ->
            if (resource!!.isLoading) {
                displayLoader()

            } else if (resource.data != null && resource.data.isNotEmpty()) {
                updateMoviesList(resource.data)

            } else
                handleErrorResponse()
        })
    }


    private fun displayLoader() {
        binding!!.moviesList.visibility = View.GONE
        binding!!.loaderLayout.visibility = View.VISIBLE
    }

    private fun hideLoader() {
        binding!!.moviesList.visibility = View.VISIBLE
        binding!!.loaderLayout.visibility = View.GONE
    }

    private fun updateMoviesList(movies: List<MovieEntity>) {
        hideLoader()
        //binding!!.emptyLayout.emptyContainer.visibility = View.GONE
        binding!!.moviesList.visibility = View.VISIBLE
        moviesListAdapter.setItems(movies)
    }

    private fun handleErrorResponse() {
        hideLoader()
        binding!!.moviesList.visibility = View.GONE
        binding!!.emptyLayout.visibility = View.VISIBLE
    }


}