package com.example.horoscopo.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.horoscopo.Data.Horoscope
import com.example.horoscopo.Data.HoroscopeProvider
import com.example.horoscopo.R
import com.example.horoscopo.adapters.HoroscopeAdapter

class ListActivity : AppCompatActivity() {

    lateinit var horoscopeList: List<Horoscope>
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: HoroscopeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_list)

        recyclerView = findViewById(R.id.recyclerView)
        horoscopeList = HoroscopeProvider.findAll()

        adapter = HoroscopeAdapter(horoscopeList, {posicion ->
                         val horoscope = horoscopeList[posicion]
                         navigateToDetail(horoscope)})

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

      }
    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
    }
    private fun navigateToDetail(horoscope: Horoscope) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("horoscope_id", horoscope.id)
        startActivity(intent)
    }

    // Función para mostrar el menú
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu_list_activity, menu)

        // Busco la opción de busqueda en el menú
        val searchMenuItem = menu?.findItem(R.id.menu_search)!!

        // Obtengo la clase del ActionView asociada a esa opción del menú
        val searchView = searchMenuItem.actionView as SearchView

        // Le asigno el listener a la busqueda
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            // función para cuando se pulsa enter
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            // función para cada vez que cambia el texto
            override fun onQueryTextChange(newText: String): Boolean {
                // Filtro la lista en base al nombre y las fechas del horóscopo
                horoscopeList = HoroscopeProvider.findAll().filter {
                    getString(it.name).contains(newText, true) ||
                    getString(it.dates).contains(newText, true)
                }

                // Le paso la nueva lista al adapter
                adapter.updateItems(horoscopeList)
                return true
            }
        })

        return true
    }
}