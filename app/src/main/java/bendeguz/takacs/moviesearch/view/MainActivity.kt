package bendeguz.takacs.moviesearch.view

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import bendeguz.takacs.moviesearch.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        const val QUERY_STRING = "query_string"
        const val MOVIE_ID = "movie_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnSearch.setOnClickListener {
            if(etQuery.text.toString().isEmpty()) {
                Toast.makeText(this, "Query has to be provided", Toast.LENGTH_SHORT).show()
            }
            else{
                val intent = Intent(this, MovieActivity::class.java)
                intent.putExtra(QUERY_STRING, etQuery.text.toString())
                startActivity(intent)

            }
        }
    }
}
