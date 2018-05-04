package com.thoughtworks.weatherapp.network

import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class WeatherService(val retrofitService: RetrofitService){

    fun getWeatherByCity(city: String){
        RetrofitService.Client.instance().getByCityName(city)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}