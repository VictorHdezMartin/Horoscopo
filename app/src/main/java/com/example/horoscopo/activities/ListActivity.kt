package com.example.horoscopo.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
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
}