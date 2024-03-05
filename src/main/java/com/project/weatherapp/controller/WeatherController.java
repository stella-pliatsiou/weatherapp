package com.project.weatherapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.weatherapp.entity.Weather;
import com.project.weatherapp.service.WeatherService;

// Class for weather controller
// Maps requests to /weather
@RestController
@RequestMapping("/weather")
public class WeatherController {

    // Weather service injection
    @Autowired
    private WeatherService weatherService;

// Gets weather for given city
    @GetMapping("/{city}")
    public Weather getWeather(@PathVariable String city) {
         // Delegate to service for weather data
        return weatherService.getWeather(city);
    }
}
