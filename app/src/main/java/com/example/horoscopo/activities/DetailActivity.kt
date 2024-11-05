package com.example.horoscopo.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.horoscopo.Data.Horoscope
import com.example.horoscopo.Data.HoroscopeProvider
import com.example.horoscopo.R
import com.example.horoscopo.utils.SessionManager

class DetailActivity : AppCompatActivity() {

    lateinit var horoscope: Horoscope
    lateinit var favoriteMenuItem: MenuItem
    lateinit var session: SessionManager
    var isFavorite = false

    override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)

      setContentView(R.layout.activity_detail)

      val id = intent.getStringExtra("horoscope_id")!!
      horoscope = HoroscopeProvider.findById(id)

      supportActionBar?.title = getString(horoscope.name)
      supportActionBar?.subtitle = getString(horoscope.dates)
      supportActionBar?.setDisplayHomeAsUpEnabled(true)                        // activamos el botón atrás del menú

      session = SessionManager(this)
      isFavorite = session.isFavorite(horoscope.id)

      findViewById<TextView>(R.id.tv).setText(horoscope.name)
      findViewById<ImageView>(R.id.iv).setImageResource(horoscope.image)
      findViewById<Button>(R.id.b).setOnClickListener {
          finish()
      }
    }

// inflamos el menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail_activity, menu)

        favoriteMenuItem = menu?.findItem(R.id.menu_favorite)!!
        setFavotiteIcon()
        return true
    }

// vemos que item se ha seleccionado
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId ){
            android.R.id.home -> {
                finish()
                return true
            }
            R.id.menu_favorite -> {
                /*
                if (isFavorite) {
                    session.setFavorite("")
                }
                else {
                    session.setFavorite(horoscope.id)
                }
                */

                if (!isFavorite)
                    session.setFavorite(horoscope.id)

                isFavorite = !isFavorite
                setFavotiteIcon()
                return true
            }
            R.id.menu_share -> {
                println("Menu compartir")
                return true
            }
        else -> {
            return super.onOptionsItemSelected(item)
            }
        }
    }
    fun setFavotiteIcon() {
        if (isFavorite) {
            favoriteMenuItem.setIcon(R.drawable.icon_favorite_selected)
        } else {
            favoriteMenuItem.setIcon(R.drawable.icon_favorite)
        }
    }
    override fun onBackPressed() {            // para el botón físico del teléfono
        if (horoscope.id == "Piscis"){
            Toast.makeText(this, "no puedes volver", Toast.LENGTH_SHORT).show()
        }
        else {
            super.onBackPressed()
        }
    }
}