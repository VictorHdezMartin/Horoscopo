package com.example.horoscopo.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.horoscopo.Data.Horoscope
import com.example.horoscopo.Data.HoroscopeProvider
import com.example.horoscopo.R

class ListActivity : AppCompatActivity() {

    lateinit var horoscopeList: List<Horoscope>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_list)

        horoscopeList = HoroscopeProvider.findAll()

    }
}