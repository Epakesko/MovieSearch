package bendeguz.takacs.moviesearch.view

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import bendeguz.takacs.moviesearch.R
import bendeguz.takacs.moviesearch.viewmodel.MoviesViewModel

class MovieDetailsFragment: Fragment() {
    companion object {
        const val MOVIE_DETAILS_FRAGMENT = "MOVIE_DETAILS_FRAGMENT"
        fun newInstance(movieId: Int) = MovieDetailsFragment().apply {
            arguments = Bundle().apply {
                putInt(MainActivity.MOVIE_ID, movieId)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.venue_details, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val viewModel = ViewModelProviders.of(this).get(MoviesViewModel::class.java!!)
        val movie = viewModel.searchResponse.value!!.results.find{
            it.id == arguments!!.getInt(MainActivity.MOVIE_ID)
        }


    }

}