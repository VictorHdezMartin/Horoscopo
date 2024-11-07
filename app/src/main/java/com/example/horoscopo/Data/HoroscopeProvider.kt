package com.example.horoscopo.Data

import com.example.horoscopo.R

class HoroscopeProvider {

// static
    companion object {
        private val horoscopeList: List<Horoscope> = listOf(
            Horoscope("aries", R.string.hName_Aries, R.string.hDate_Aries, R.drawable.icon_aries, R.string.hRef_Aries, R.string.hDetalle_Aries),
            Horoscope("taurus", R.string.hName_Tauro, R.string.hDate_Tauro, R.drawable.icon_tauro, R.string.hRef_Tauro, R.string.hDetalle_Tauro),
            Horoscope("gemini", R.string.hName_Geminis, R.string.hDate_Geminis, R.drawable.icon_geminis, R.string.hRef_Geminis, R.string.hDetalle_Geminis),
            Horoscope("cancer", R.string.hName_Cancer, R.string.hDate_Cancer, R.drawable.icon_cancer, R.string.hRef_Cancer, R.string.hDetalle_Cancer),
            Horoscope("leo", R.string.hName_Leo, R.string.hDate_Leo, R.drawable.icon_leo, R.string.hRef_Leo, R.string.hDetalle_Leo),
            Horoscope("virgo", R.string.hName_Virgo, R.string.hDate_Virgo, R.drawable.icon_virgo, R.string.hRef_Virgo, R.string.hDetalle_Virgo),
            Horoscope("libra", R.string.hName_Libra, R.string.hDate_Libra, R.drawable.icon_libra, R.string.hRef_Libra, R.string.hDetalle_Libra),
            Horoscope("scorpio", R.string.hName_Escorpio, R.string.hDate_Escorpio, R.drawable.icon_escorpio, R.string.hRef_Escorpio, R.string.hDetalle_Escorpio),
            Horoscope("sagittarius", R.string.hName_Sagitario, R.string.hDate_Sagitario, R.drawable.icon_sagitario, R.string.hRef_Sagitario, R.string.hDetalle_Sagitario),
            Horoscope("capricornio", R.string.hName_Capricornio, R.string.hDate_Capricornio, R.drawable.icon_capricornio, R.string.hRef_Capricornio, R.string.hDetalle_Capricornio),
            Horoscope("aquarius", R.string.hName_Acuario, R.string.hDate_Acuario, R.drawable.icon_acuario, R.string.hRef_Acuario, R.string.hDetalle_Acuario),
            Horoscope("pisces", R.string.hName_Piscis, R.string.hDate_Piscis, R.drawable.icon_piscis, R.string.hRef_Piscis, R.string.hDetalle_Piscis)
        )

    fun findAll(): List<Horoscope> {
        return horoscopeList
    }
    fun findById(id: String): Horoscope {
        return horoscopeList.find { it.id == id }!!
    }
  }
}
