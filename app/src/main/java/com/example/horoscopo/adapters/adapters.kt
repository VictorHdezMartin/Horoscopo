package com.example.horoscopo.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.horoscopo.Data.Horoscope
import com.example.horoscopo.R
import com.example.horoscopo.utils.SessionManager

class HoroscopeAdapter (var items: List<Horoscope>, val onItemClick:(Int)-> Unit): RecyclerView.Adapter<HoroscopeViewHolder> () {

    var selectedItem = -1  // elemento seleccionado para hacerlo visible

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HoroscopeViewHolder {
       val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_horoscope, parent, false)
       return HoroscopeViewHolder(view)
    }
    override fun getItemCount(): Int {
       return items.size
    }
    override fun onBindViewHolder(holder: HoroscopeViewHolder, position: Int) {
       val horoscope = items[position]

       holder.render(horoscope, selectedItem)

       holder.itemView.setOnClickListener(){
            onItemClick(position)
       }

       holder.itemView.setOnLongClickListener() {   // MOSTRAMOS O NO EL DETALLE DEL ITEM
            val itemToClose = selectedItem                  // hay que pasar el elemento ha "GONE"

            if (selectedItem == position) {                 // si volvemos a hacer click sobre elemento selecccionado, lo pasa a "GONE"
                selectedItem = -1
            }
            else {
                selectedItem = position                     // si NO lo hacemos VISIBLE
                notifyItemChanged(selectedItem)
            }

            notifyItemChanged(itemToClose)                  // el elemento ha cambiado y hay que pasarlo a GONE
            true
       }
    }
    fun updateItems(items: List<Horoscope>) {
        this.items = items
        notifyDataSetChanged()
    }
}

class HoroscopeViewHolder(view: View): RecyclerView.ViewHolder (view){

    var nameTextView: TextView = view.findViewById(R.id.nameTextView)
    var dateTextView: TextView = view.findViewById(R.id.dateTextView)
    var symbolImageView: ImageView = view.findViewById(R.id.symbolImageView)
    var detalleTextView: TextView = view.findViewById(R.id.detalleTextView)
    var detalleLinearLayoutView: LinearLayout =view.findViewById(R.id.detalleLinearLayoutView)
    var favoriteImageView: ImageView = view.findViewById(R.id.favoriteImageView)

    fun render(horoscope: Horoscope, selectedItem: Int) {

        var context = itemView.context

        fillItems(horoscope)               // rellenamos los dtos de cada item
        reDibujarCelda(context)            // pintamos los items (pares e impares)
        showDetail(selectedItem)           // mostramos el detalle del item seleccionado
        showFavorito(horoscope)            // mostramos si el item seleccionado es o no favorito
    }
    fun fillItems(horoscope: Horoscope){
        nameTextView.setText(horoscope.name)
        dateTextView.setText(horoscope.dates)
        symbolImageView.setImageResource(horoscope.image)
        detalleTextView.setText(horoscope.referencia)
    }
    fun reDibujarCelda(context: Context){
        if (adapterPosition % 2 != 0) {
            itemView.setBackgroundColor(context.getColor(R.color.white))
            nameTextView.setTextColor(context.getColor(R.color.granate))
            dateTextView.setTextColor(context.getColor(R.color.black))
        } else {
            itemView.setBackgroundColor(context.getColor(R.color.gris))
            nameTextView.setTextColor(context.getColor(R.color.granate))
            dateTextView.setTextColor(context.getColor(R.color.marino))
        }
    }
    fun showDetail(selectedItem: Int){
        if (adapterPosition == selectedItem) {
            detalleLinearLayoutView.visibility = View.VISIBLE
        } else {
            detalleLinearLayoutView.visibility = View.GONE
        }
    }
    fun showFavorito(horoscope: Horoscope){
        if (SessionManager(itemView.context).isFavorite(horoscope.id)) {
            favoriteImageView.visibility = View.VISIBLE
        }
        else {
            favoriteImageView.visibility = View.GONE
        }
    }
}