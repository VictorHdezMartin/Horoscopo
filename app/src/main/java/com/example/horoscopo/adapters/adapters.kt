package com.example.horoscopo.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.horoscopo.Data.Horoscope
import com.example.horoscopo.R

class HoroscopeAdapter (val items: List<Horoscope>): RecyclerView.Adapter<HoroscopeViewHolder> () {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HoroscopeViewHolder {

       val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_horoscope, parent, false)
       return HoroscopeViewHolder(view)
    }

    override fun getItemCount(): Int {
       return items.size
    }

    override fun onBindViewHolder(holder: HoroscopeViewHolder, position: Int) {
       val horoscope = items[position]
       holder.render(horoscope)
    }
}

class HoroscopeViewHolder(view: View): RecyclerView.ViewHolder (view){

    var nameTextView: TextView = view.findViewById(R.id.nameTextView)
    var dateTextView: TextView = view.findViewById(R.id.dateTextView)
    var symbolImageView: ImageView = view.findViewById(R.id.symbolImageView)

    fun render(horoscope: Horoscope){

      // llamada fuera del activity para poder acceder a los metodos
        var context = itemView.context
      //  nameTextView.text = context.getString(horoscope.name)
      //  symbolImageView.setImageDrawable((context.getDrawable(horoscope.image)))

         if (horoscope.pos % 2 != 0) {                                                              // vemos si el item es par o impar
             itemView.setBackgroundColor(context.getColor(R.color.white))
             nameTextView.setTextColor(context.getColor(R.color.granate))
             dateTextView.setTextColor(context.getColor(R.color.black))
         }
       else {
             itemView.setBackgroundColor(context.getColor(R.color.gris))
             nameTextView.setTextColor(context.getColor(R.color.granate))
             dateTextView.setTextColor(context.getColor(R.color.marino))
         }

       nameTextView.setText(horoscope.name)
       dateTextView.setText(horoscope.dates)
       symbolImageView.setImageResource(horoscope.image)
    }
}