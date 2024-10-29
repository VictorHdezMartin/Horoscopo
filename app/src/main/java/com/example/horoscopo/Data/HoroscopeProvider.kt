package com.example.horoscopo.Data

import com.example.horoscopo.R

class HoroscopeProvider {

// static
    companion object {
        private val horoscopeList: List<Horoscope> = listOf(
            Horoscope("Aries", R.string.hName_Aries, R.string.hDate_Aries, R.drawable.icon_aries),
            Horoscope("Tauro", R.string.hName_Tauro, R.string.hDate_Tauro, R.drawable.icon_tauro),
            Horoscope("Geminis", R.string.hName_Geminis, R.string.hDate_Geminis,R.drawable.icon_geminis),
            Horoscope("Cancer", R.string.hName_Cancer, R.string.hDate_Cancer,R.drawable.icon_cancer),
            Horoscope("Leo", R.string.hName_Leo, R.string.hDate_Leo,R.drawable.icon_leo),
            Horoscope("Virgo", R.string.hName_Virgo, R.string.hDate_Virgo,R.drawable.icon_virgo),
            Horoscope("Libra", R.string.hName_Libra, R.string.hDate_Libra,R.drawable.icon_libra),
            Horoscope("Escorpio", R.string.hName_Escorpio, R.string.hDate_Escorpio,R.drawable.icon_escorpio),
            Horoscope("Sagitario", R.string.hName_Sagitario, R.string.hDate_Sagitario,R.drawable.icon_sagitario),
            Horoscope("Capricornio", R.string.hName_Capricornio, R.string.hDate_Capricornio,R.drawable.icon_capricornio),
            Horoscope("Acuario", R.string.hName_Acuario, R.string.hDate_Acuario,R.drawable.icon_acuario),
            Horoscope("Piscis", R.string.hName_Piscis, R.string.hDate_Piscis, R.drawable.icon_piscis)
    )
    fun findAll(): List<Horoscope> {
        return horoscopeList
    }
    fun findById(id: String): Horoscope {
        return horoscopeList.find { it.id == id }!!
    }
  }
}
