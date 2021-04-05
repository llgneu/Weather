package com.example.weather.logic.model

import com.google.gson.annotations.SerializedName

class RealtimeResponse(val status:String, val result:Result) {
    data class Result(val realtime:Realtime)

    data class Realtime(val skycon:String, val temperature:Float,
                @SerializedName("air_quality") val air_Quality:Air_Quality)

    data class Air_Quality(val aqi:AQI)

    data class AQI(val chn:Float)


}