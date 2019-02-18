package bendeguz.takacs.moviesearch.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import bendeguz.takacs.moviesearch.model.Movie
import bendeguz.takacs.moviesearch.model.SearchResponse
import bendeguz.takacs.moviesearch.repository.MovieRepository


class MoviesViewModel : ViewModel() {
    var searchResponse: LiveData<SearchResponse> = MutableLiveData<SearchResponse>()
    private var queryString: String? = null

    fun initialize(query: String, page: Int){
        queryString = query
        searchResponse = MovieRepository().getMovieList(queryString as String, page)
    }

    fun getMovie(movieId: Int): Movie? {
        return searchResponse.value!!.results.find{
            it.id == movieId
        }
    }
}