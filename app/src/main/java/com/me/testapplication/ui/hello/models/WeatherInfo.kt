package com.me.testapplication.ui.hello.models

class WeatherInfo(
    val temperature: String? = null,
    val weatherState: WeatherState? = null
)

enum class WeatherState(state: String) {
    ERROR("error"),
    WINDY("windy"),
    SUNNY("sunny"),
    CLOUDY("cloudy")
}