package com.thoughtworks.weatherapp.detail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.thoughtworks.weatherapp.R
import com.thoughtworks.weatherapp.model.WeatherInfo
import com.thoughtworks.weatherapp.network.WeatherService
import kotlinx.android.synthetic.main.activity_main.*

class DetailActivity : AppCompatActivity(), DetailView {

    private lateinit var presenter: DetailPresenter
    private val weatherService: WeatherService = WeatherService.Client.instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = DetailPresenter(this, weatherService)
        presenter.fetchWeather()
    }

    override fun showLoader() {

    }

    override fun hideLoader() {

    }

    override fun updateWeather(weather: WeatherInfo) {
        city.text = weather.name
    }
}
