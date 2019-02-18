package bendeguz.takacs.moviesearch.repository

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.LiveData
import bendeguz.takacs.moviesearch.model.Movie
import bendeguz.takacs.moviesearch.network.TMDBApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import bendeguz.takacs.moviesearch.model.SearchResponse
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

suspend fun <T> Call<T>.runCoroutine() : T = suspendCoroutine {
    this.enqueue(object : Callback<T>{
        override fun onFailure(call: Call<T>, t: Throwable) {
            it.resumeWithException(t)
        }
        override fun onResponse(call: Call<T>, response: Response<T>) {
            it.resume(response.body()!!)
        }
    })
}

class MovieRepository {
    private val tmdbApi: TMDBApi

    init{
        val retrofit = Retrofit.Builder()
            .baseUrl(TMDBApi.TMDB_ENDPOINT_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        this.tmdbApi = retrofit.create(TMDBApi::class.java)
    }

    fun getMovieList(query: String): LiveData<SearchResponse> {
        val data = MutableLiveData<SearchResponse>()

        tmdbApi.searchMovies(TMDBApi.TMDB_API_KEY, query).enqueue(object: Callback<SearchResponse>    {
            override fun onFailure(call: Call<SearchResponse>?, t: Throwable?) {

            }

            override fun onResponse(call: Call<SearchResponse>?, response: Response<SearchResponse>) {
                GlobalScope.launch (Dispatchers.Main) {
                    val resultsAsync = mutableListOf<Deferred<Movie>>()
                    val results = mutableListOf<Movie>()
                    val response = response.body()


                    for (movie in response!!.results) {
                        resultsAsync.add(async { tmdbApi.searchMovie(movie.id, TMDBApi.TMDB_API_KEY).runCoroutine() })
                    }
                    for (a in resultsAsync) {
                        results.add(a.await())
                    }

                    response?.results = results
                    data.value = response
                }
            }
        })
        return data
    }
}