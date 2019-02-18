package bendeguz.takacs.moviesearch.view

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import bendeguz.takacs.moviesearch.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        const val QUERY_STRING = "query_string"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnSearch.setOnClickListener {
            val intent = Intent(this, MovieActivity::class.java)
            intent.putExtra(QUERY_STRING, etQuery.text.toString())
            startActivity(intent)
        }
    }
}
