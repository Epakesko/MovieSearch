package bendeguz.takacs.moviesearch.network

import bendeguz.takacs.moviesearch.model.Movie
import bendeguz.takacs.moviesearch.model.SearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TMDBApi {
    companion object {
        const val TMDB_ENDPOINT_URL = "https://api.themoviedb.org/3/"
        const val TMDB_API_KEY = "ab322048440e3713904c8dde361213a1"
    }

    @GET("search/movie")
    fun searchMovies(@Query("api_key") api_key: String,
                     @Query("query") query: String
    ): Call<SearchResponse>

    @GET("movie/{movieId}")
    fun searchMovie(@Path("movieId") movieId: Int,
                     @Query("api_key") api_key: String
    ): Call<Movie>

}