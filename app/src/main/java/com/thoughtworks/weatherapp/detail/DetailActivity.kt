package com.thoughtworks.weatherapp.detail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.thoughtworks.weatherapp.R

class DetailActivity : AppCompatActivity(), DetailView {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun updateWeather() {

    }
}
