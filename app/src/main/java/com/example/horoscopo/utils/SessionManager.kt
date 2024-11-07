package com.example.horoscopo.utils

import android.content.Context

class SessionManager(val context: Context){

    private val prefs = context.getSharedPreferences("horoscope_session", Context.MODE_PRIVATE)
    fun setFavorite(horoscopeId: String, isFavorite: Boolean){
        val editor = prefs.edit()
        editor.putBoolean(horoscopeId, isFavorite)
        editor.apply()
    }
    fun getFavorite (horoscopeId: String): Boolean {
        return prefs.getBoolean(horoscopeId, false)
    }
}