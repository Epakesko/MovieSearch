package bendeguz.takacs.moviesearch.model

data class SearchResponse(
    val page: Int,
    val total_pages: Int,
    val movies: List<Movie>
)