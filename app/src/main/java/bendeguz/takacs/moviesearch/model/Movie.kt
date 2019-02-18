package bendeguz.takacs.moviesearch.model

data class Movie(
    val vote_count: Int,
    val id: Int,
    val vote_average: Double,
    val title: String,
    val popularity: Double,
    val poster_path: String,
    val backdrop_path: String,
    val overview: String,
    val release_date: String
)