package com.thoughtworks.weatherapp.detail

import com.nhaarman.mockito_kotlin.*
import com.thoughtworks.weatherapp.model.Main
import com.thoughtworks.weatherapp.model.WeatherInfo
import com.thoughtworks.weatherapp.model.Wind
import com.thoughtworks.weatherapp.network.WeatherService
import org.junit.Before
import org.junit.Test
import rx.Observable
import rx.schedulers.TestScheduler


class DetailPresenterTest{

    lateinit var presenter: DetailPresenter
    lateinit var view: DetailView
    lateinit var retrofitService: WeatherService
    lateinit var testScheduler: TestScheduler
    private val weatherInfo = WeatherInfo(ArrayList(), Main(2.0,1,1,2.0,2.0),Wind(2.0,1), 1, 1, "Bng")


    @Before
    fun setUp() {
        testScheduler = TestScheduler()
        view = mock {  }
        retrofitService = mock { on { getByCityName(any(), any()) } doReturn Observable.just(weatherInfo) }
        presenter = DetailPresenter(view, retrofitService, testScheduler, testScheduler)
    }

    @Test
    fun shouldFetchWeather() {
        presenter.fetchWeather()

        verify(retrofitService).getByCityName(eq("Bangalore"), any())
        verify(view).showLoader()
        testScheduler.triggerActions()
        verify(view).updateWeather(weatherInfo)
        verify(view).hideLoader()
    }
}
