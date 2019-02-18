package bendeguz.takacs.moviesearch.repository

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.LiveData
import bendeguz.takacs.moviesearch.model.Movie
import bendeguz.takacs.moviesearch.network.TMDBApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import bendeguz.takacs.moviesearch.model.SearchResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


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
        val movies = mutableListOf<Movie>()

        tmdbApi.searchMovies(TMDBApi.TMDB_API_KEY, query).enqueue(object: Callback<SearchResponse>    {
            override fun onFailure(call: Call<SearchResponse>?, t: Throwable?) {

            }

            override fun onResponse(call: Call<SearchResponse>?, response: Response<SearchResponse>) {
                data.value = response.body()
                data.value?.results?.forEach {movie ->
                    tmdbApi.searchMovie(movie.id, TMDBApi.TMDB_API_KEY).enqueue(object: Callback<Movie>    {
                        override fun onFailure(call: Call<Movie>?, t: Throwable?) {

                        }

                        override fun onResponse(call: Call<Movie>?, response: Response<Movie>) {
                            movies.add(response.body()!!)
                        }
                    })
                }
                data.value?.results = movies
            }
        })

        return data
    }
}