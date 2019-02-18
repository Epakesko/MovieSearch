package bendeguz.takacs.moviesearch.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import bendeguz.takacs.moviesearch.R
import bendeguz.takacs.moviesearch.model.Movie
import kotlinx.android.synthetic.main.movie_list_row.view.*

class MoviesAdapter(private val movieList: MutableList<Movie>) : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    var itemClickListener: MovieClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_list_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movieList[position]
        val budget = "$${movie.budget}"

        holder.movieId = movie.id
        holder.tvTitle.text = movie.title
        holder.tvBudget.text = budget
    }

    override fun getItemCount() = movieList.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var movieId: Int? = null
        val tvTitle: TextView = itemView.tvTitle
        val tvBudget: TextView = itemView.tvBudget

        init {
            itemView.setOnClickListener {
                movieId?.let { movieId -> itemClickListener?.onItemClick(movieId) }
            }
        }
    }

    interface MovieClickListener {
        fun onItemClick(movieId: Int)
    }
}