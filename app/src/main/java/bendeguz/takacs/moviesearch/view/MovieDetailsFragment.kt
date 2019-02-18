package bendeguz.takacs.moviesearch.view

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import bendeguz.takacs.moviesearch.R
import bendeguz.takacs.moviesearch.viewmodel.MoviesViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.movie_details.*

class MovieDetailsFragment: Fragment() {
    companion object {
        private const val MOVIE_TITLE = "MOVIE_TITLE"
        private const val MOVIE_DESCRIPTION = "MOVIE_DESCRIPTION"
        private const val MOVIE_RATING = "MOVIE_RATING"
        private const val MOVIE_PICTURE_URL = "MOVIE_PICTURE_URL"
        private const val MOVIE_RELEASE_DATE = "MOVIE_RELEASE_DATE"
        fun newInstance(movieTitle: String, movieDesc: String, movieRating: Double, moviePictureUrl: String, movieReleaseDate: String) = MovieDetailsFragment().apply {
            arguments = Bundle().apply {
                putString(MOVIE_TITLE, movieTitle)
                putString(MOVIE_DESCRIPTION, movieDesc)
                putDouble(MOVIE_RATING, movieRating)
                putString(MOVIE_PICTURE_URL, moviePictureUrl)
                putString(MOVIE_RELEASE_DATE, movieReleaseDate)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.movie_details, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val title = arguments!!.getString(MOVIE_TITLE)
        val desc = arguments!!.getString(MOVIE_DESCRIPTION)
        val ratings = arguments!!.getDouble(MOVIE_RATING)
        val url = arguments!!.getString(MOVIE_PICTURE_URL)
        val date = arguments!!.getString(MOVIE_RELEASE_DATE)

        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w300/$url")
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.not_applicable)
            )
            .into(ivPicture)

        tvTitle.text = title
        tvDescription.text = desc
        tvRating.text = ratings.toString()
        tvDate.text = date
    }

}