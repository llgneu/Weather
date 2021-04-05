package com.example.weather.logic

import androidx.lifecycle.liveData
import com.example.weather.logic.dao.PlaceDao
import com.example.weather.logic.network.WeatherNetwork

import com.example.weather.logic.model.Place
import com.example.weather.logic.model.Weather
//import com.weather.android.logic.model.Weather
//import com.weather.android.logic.network.SunnyWeatherNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import java.lang.Exception
import kotlin.coroutines.CoroutineContext

object Respository {
    fun searchPlaces(query:String) = fire(Dispatchers.IO){
            val placeResponse = WeatherNetwork.searchPlaces(query)
            if(placeResponse.status == "ok"){
                val places = placeResponse.places
                Result.success(places)
            }else{
                Result.failure(RuntimeException("response status is ${placeResponse.status}"))
            }


    }

    fun refreshWeather(lng:String, lat:String) = fire(Dispatchers.IO) {

            coroutineScope {
                val deferredRealtime = async {
                    WeatherNetwork.getRealtimeWeather(lng, lat)
                }

                val deferredDaily = async {
                    WeatherNetwork.getDailyWeather(lng, lat)
                }

                val realtimeResponse = deferredRealtime.await()
                val dailyResponse = deferredDaily.await()

                if (realtimeResponse.status == "ok" && dailyResponse.status == "ok"){
                    val weather = Weather(realtimeResponse.result.realtime, dailyResponse.result.daily)
                    Result.success(weather)
                }else{
                    Result.failure(
                            java.lang.RuntimeException(
                                    "realtime response status is ${realtimeResponse.status} + " +
                                            "daily response status is ${dailyResponse.status}"
                            )
                    )
                }
            }


    }

    private fun<T> fire(context: CoroutineContext, block: suspend () -> Result<T>) =
            liveData<Result<T>>(context){
                val result = try {
                    block()
                }catch (e:Exception){
                    Result.failure<T>(e)
                }
                emit(result)
            }
    
    fun savePlace(place: Place) = PlaceDao.savaPlace(place)

    fun getSavePlace() = PlaceDao.getSavedPlace()

    fun isPlaceSaved() = PlaceDao.isPlaceSaved()
}