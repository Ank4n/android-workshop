package com.thoughtworks.weatherapp.network

import com.thoughtworks.weatherapp.model.WeatherInfo
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

private const val APP_ID = "e3d72b0711b57e4c8da67629201b3d60"

interface RetrofitService {
    @GET("2.5/weather")
    fun getByCityName(@Query("q") query: String,
                      @Query("appid") appID: String = APP_ID)
            : Observable<WeatherInfo>


    object Client {

        private const val BASE_URL = "http://api.openweathermap.org/data/"

        private val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()

        private val weatherAPIService = retrofit.create(RetrofitService::class.java)

        fun instance(): RetrofitService = weatherAPIService
    }

}
