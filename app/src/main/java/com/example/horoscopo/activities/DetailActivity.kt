package com.example.horoscopo.activities

import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Website.URL
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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class DetailActivity : AppCompatActivity() {

    lateinit var horoscope: Horoscope
    lateinit var favoriteMenuItem: MenuItem
    lateinit var session: SessionManager
    var isFavorite = false

    lateinit var symbolImageView: ImageView
    lateinit var luckTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)

      setContentView(R.layout.activity_detail)

      val id = intent.getStringExtra("horoscope_id")!!
      horoscope = HoroscopeProvider.findById(id)

      supportActionBar?.title = getString(horoscope.name)
      supportActionBar?.subtitle = getString(horoscope.dates)
      supportActionBar?.setDisplayHomeAsUpEnabled(true)                        // activamos el botón atrás del menú

      session = SessionManager(this)
      isFavorite = session.getFavorite(horoscope.id)

   // Busco los componenetes visuales

      luckTextView = findViewById(R.id.luckTextView)
      symbolImageView = findViewById(R.id.symbolImageView)

      symbolImageView.setImageResource(horoscope.image)

      getHoroscopeLuck()
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
                isFavorite = !isFavorite

                session.setFavorite(horoscope.id, isFavorite)

                setFavotiteIcon()
                return true
            }
            R.id.menu_share -> {
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

    fun getHoroscopeLuck() {
        var result = "Antes de hacer la llamada"

        CoroutineScope(Dispatchers.IO).launch {
            val url = URL("https://horoscope-app-api.vercel.app/api/v1/get-horoscope/daily?sign=${horoscope.id}&day=TODAY")
            val con = url.openConnection() as HttpsURLConnection
            con.requestMethod = "GET"
            val responseCode = con.responseCode
            println("Response Code :: $responseCode")
            if (responseCode == HttpsURLConnection.HTTP_OK) { // connection ok
                val jsonResponse = readStream(con.inputStream).toString()
                result = JSONObject(jsonResponse).getJSONObject("data").getString("horoscope_data")
            } else {
                result = "Hubo un error en la llamada"
            }

            CoroutineScope(Dispatchers.Main).launch {
                luckTextView.text = result
            }
        }
    }

    private fun readStream (inputStream: InputStream) : StringBuilder {
        val reader = BufferedReader(InputStreamReader(inputStream))
        val response = StringBuilder()
        var line: String?
        while (reader.readLine().also { line = it } != null) {
            response.append(line)
        }
        reader.close()
        return response
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