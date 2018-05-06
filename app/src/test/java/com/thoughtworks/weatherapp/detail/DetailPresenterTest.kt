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


class DetailPresenterTest {


    private val weatherInfo = WeatherInfo(ArrayList(), Main(2.0,1,1,2.0,2.0), Wind(2.0,1), 1, 1, "Bng")
    lateinit var presenter: DetailPresenter
    lateinit var view: DetailView
    lateinit var weatherService: WeatherService
    lateinit var testScheduler: TestScheduler


    @Before
    fun setUp() {
        view = mock { }
        weatherService = mock {
            on { getByCityName(eq("Bangalore"), any()) } doReturn Observable.just(weatherInfo)}
        testScheduler = TestScheduler()
        presenter = DetailPresenter(view, weatherService, testScheduler, testScheduler)
    }

    @Test
    fun shouldFetchWeather() {
        //when
        presenter.onCreate()

        //then
        verify(view).showLoader()
        verify(weatherService).getByCityName(eq("Bangalore"), any())

        reset(view)
        testScheduler.triggerActions()

        verify(view).hideLoader()
        verify(view, times(1)).updateWeather(weatherInfo)
        verify(view, never()).showLoader()

    }

    @Test
    fun shouldHandleNetworkError(){
        val errorWeatherService: WeatherService = mock {
            on { getByCityName(eq("Bangalore"), any()) } doReturn Observable.error(Exception())}

        presenter = DetailPresenter(view, errorWeatherService, testScheduler, testScheduler)
        presenter.onCreate()

        verify(errorWeatherService).getByCityName(eq("Bangalore"), any())
        testScheduler.triggerActions()

        verify(view).hideLoader()
        verify(view).handleError()
        verify(view, never()).updateWeather(weatherInfo)

    }
}
