package com.thoughtworks.weatherapp.detail

import com.thoughtworks.weatherapp.model.WeatherInfo

interface DetailView{
    fun updateWeather(weather: WeatherInfo)
    fun showLoader()
    fun hideLoader()
}