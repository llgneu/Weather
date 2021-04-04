package com.example.weather.ui.place

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.weather.logic.Respository
import com.example.weather.logic.model.Place

class PlaceViewModel:ViewModel(){
    private val searchLivaData = MutableLiveData<String>()

    val placelist = ArrayList<Place>()

    val placeLiveData = Transformations.switchMap(searchLivaData){query->
        Respository.searchPlaces(query)
    }

    fun searchPlaces(query:String){
        searchLivaData.value = query
    }
}