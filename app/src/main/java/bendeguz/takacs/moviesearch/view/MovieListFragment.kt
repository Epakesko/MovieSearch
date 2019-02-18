package bendeguz.takacs.moviesearch.view

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.arch.lifecycle.ViewModelProviders
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import bendeguz.takacs.moviesearch.R
import bendeguz.takacs.moviesearch.adapter.MoviesAdapter
import bendeguz.takacs.moviesearch.model.SearchResponse
import bendeguz.takacs.moviesearch.viewmodel.MoviesViewModel
import kotlinx.android.synthetic.main.movie_list.*


class MovieListFragment : Fragment(), MoviesAdapter.MovieClickListener {

    companion object {
        const val MOVIE_LIST_FRAGMENT = "MOVIE_LIST_FRAGMENT"
        fun newInstance(query: String) = MovieListFragment().apply {
            arguments = Bundle().apply {
                putString(MainActivity.QUERY_STRING, query)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.movie_list, container, false)
    }

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
                    rvMovies.adapter = MoviesAdapter(response.results)
                    (rvMovies.adapter as MoviesAdapter).itemClickListener = this
                    btnPreviousResults.visibility = if(response.page == 1) View.GONE else View.VISIBLE
                    btnNextResults.visibility = if(response.page == response.total_pages) View.VISIBLE else View.GONE
                }
            })
    }

    override fun onItemClick(movieId: Int) {
        val fragment = MovieDetailsFragment.newInstance(movieId)
        val ft = fragmentManager!!.beginTransaction()
        ft.replace(R.id.fragmentContainer, fragment, "MOVIE_DETAILS_FRAGMENT")
        ft.addToBackStack("MOVIE_DETAILS_FRAGMENT")
        ft.commit()
    }
}