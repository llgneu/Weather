package com.example.weather.ui.place

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.R
import com.example.weather.logic.model.Place

class PlaceAdapter(private val fragment:Fragment, private val placeList:List<Place>):
    RecyclerView.Adapter<PlaceAdapter.ViewHolder>(){

    inner class ViewHolder(view:View) :RecyclerView.ViewHolder(view){
        val placeName :TextView = view.findViewById(R.id.placeName)
        val placeAdress: TextView = view.findViewById(R.id.placeAdress)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.place_item, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount()=placeList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val place = placeList[position]

        holder.placeName.text = place.name
        holder.placeAdress.text = place.address
    }
}