package com.thoughtworks.weatherapp.model

data class WeatherInfo(
        val weather: List<Weather>,
        val main: Main,
        val wind: Wind,
        val dt: Int,
        val id: Int,
        val name: String)

data class Wind(
        val speed: Double,
        val deg: Int
)

data class Weather(
        val id: Int,
        val main: String,
        val description: String,
        val icon: String
)

data class Main(
        val temp: Double,
        val pressure: Int,
        val humidity: Int,
        val temp_min: Double,
        val temp_max: Double
)
