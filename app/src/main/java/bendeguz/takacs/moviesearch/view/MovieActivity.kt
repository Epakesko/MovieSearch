package bendeguz.takacs.moviesearch.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import bendeguz.takacs.moviesearch.R
import kotlinx.android.synthetic.main.activity_main.*

class MovieActivity : AppCompatActivity() {
    private lateinit var query: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_movie)

        query = intent.getStringExtra(MainActivity.QUERY_STRING)

        val movieListFragment = MovieListFragment.newInstance(query)
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragmentContainer, movieListFragment, MovieListFragment.MOVIE_LIST_FRAGMENT)
        ft.commit()
    }
}
