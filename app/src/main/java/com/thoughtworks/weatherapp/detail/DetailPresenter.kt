package com.thoughtworks.weatherapp.detail

import com.thoughtworks.weatherapp.network.RetrofitService
import rx.Scheduler
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class DetailPresenter(
        val view: DetailView,
        private val retrofitService: RetrofitService,
        private val processScheduler: Scheduler = Schedulers.io(),
        private val androidScheduler: Scheduler = AndroidSchedulers.mainThread()) {

    fun fetchWeather() {
        retrofitService.getByCityName("Bangalore")
                .subscribeOn(processScheduler)
                .observeOn(androidScheduler)
                .subscribe(
                        { _ -> view.updateWeather()
                        })

    }

}