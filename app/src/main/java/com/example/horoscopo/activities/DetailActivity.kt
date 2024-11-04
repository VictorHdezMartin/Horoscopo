package com.example.horoscopo.activities

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.horoscopo.Data.Horoscope
import com.example.horoscopo.Data.HoroscopeProvider
import com.example.horoscopo.R
import com.example.horoscopo.utils.SessionManager

class DetailActivity : AppCompatActivity() {

    lateinit var horoscope: Horoscope
    lateinit var session: SessionManager
    var isFavorite: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_detail)

        val id = intent.getStringExtra("horoscope_id").toString()
        horoscope = HoroscopeProvider.findById(id)

        findViewById<TextView>(R.id.tv).setText(horoscope.name)
        findViewById<ImageView>(R.id.iv).setImageResource(horoscope.image)

        supportActionBar?.title = getString(horoscope.name)
        supportActionBar?.subtitle = getString(horoscope.dates)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)                        // activamos el botón atrás del menú

        session = SessionManager(this)


        if (session.getFavorite() == horoscope.id) {
            println("horoscopo es favorito")
            isFavorite = true
        }
        else {
            println("horoscopo NO es favorito")

        }
        findViewById<Button>(R.id.b).setOnClickListener{
            finish()
        }
    }

// inflamos el menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail_activity, menu)
        return true
    }

// vemos que item se ha seleccionado
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId ){

            android.R.id.home -> {          // funcionalidad botón atras del menu activity
                println("menu home")        // se ve en el logcat
                finish()                    // se vuelve al menu anterior
                return true
            }

            R.id.menu_favorite -> {
                val editor = session.edit()
                println("menu favorito")     // se ve en el logcat
                if (isFavorite) {
                    editor.remove("favorite_horoscope")
                } else
                    editor.putString("favorite_horoscope", horoscope.id)

                editor.apply()
                return true
            }

            R.id.menu_share -> {
                println("menu compartir")    // se ve en el logcat
                return true
            }
        else -> {
            return super.onOptionsItemSelected(item)
                }
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