package bendeguz.takacs.moviesearch.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import bendeguz.takacs.moviesearch.model.SearchResponse
import bendeguz.takacs.moviesearch.repository.MovieRepository


class MoviesViewModel : ViewModel() {
    var searchResponse: LiveData<SearchResponse> = MutableLiveData<SearchResponse>()
    private var queryString: String? = null

    fun initialize(query: String){
        queryString = query
        searchResponse = MovieRepository().getMovieList(queryString as String)
    }
}