package bendeguz.takacs.moviesearch.view

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.arch.lifecycle.ViewModelProviders
import bendeguz.takacs.moviesearch.adapter.MovieAdapter
import bendeguz.takacs.moviesearch.model.SearchResponse
import bendeguz.takacs.moviesearch.viewmodel.MoviesViewModel


class MovieListFragment : Fragment() {
    companion object {
        const val MOVIE_LIST_FRAGMENT = "query_string"
        fun newInstance(query: String) = MovieListFragment().apply {
            arguments = Bundle().apply {
                putString(MainActivity.QUERY_STRING, query)
            }
        }
    }

    private val movieAdapter: MovieAdapter? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val viewModel = ViewModelProviders.of(this).get(MoviesViewModel::class.java!!)
        viewModel.initialize(arguments!!.getString(MainActivity.QUERY_STRING)!!)

        observeViewModel(viewModel)
    }

    private fun observeViewModel(viewModel: MoviesViewModel) {
        viewModel.searchResponse.observe(this,
            Observer<SearchResponse> { response ->
                if (response != null) {
                    val alma = "alma"
                    //movieAdapter!!.setMovieList(response.movies)
                }
            })
    }
}