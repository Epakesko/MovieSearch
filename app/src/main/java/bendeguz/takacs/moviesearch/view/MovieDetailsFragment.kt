package bendeguz.takacs.moviesearch.view

import android.os.Bundle
import android.support.v4.app.Fragment

class MovieDetailsFragment: Fragment() {

    companion object {
        const val MOVIE_DETAILS_FRAGMENT = "MOVIE_DETAILS_FRAGMENT"
        fun newInstance(movieId: Int) = MovieDetailsFragment().apply {
            arguments = Bundle().apply {
                putInt(MainActivity.MOVIE_ID, movieId)
            }
        }
    }
}