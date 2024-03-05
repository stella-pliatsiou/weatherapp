package com.project.weatherapp.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.weatherapp.entity.Weather;
import com.project.weatherapp.repository.WeatherRepository;

@Service
public class WeatherService {

    @Autowired  // Auto-wiring dependency
    private WeatherRepository weatherRepository;

    public Weather getWeather(String city) {
        RestTemplate restTemplate = new RestTemplate();
        String uri = "https://wttr.in/" + city + "?format=j1"; // Construct API URL
        String weatherInfoJson = restTemplate.getForObject(uri, String.class);  // Get weather info as JSON
        Weather weather = new Weather(); // Create new Weather object
        ObjectMapper mapper = new ObjectMapper(); // Create Object Mapper

        // Try-catch block to handle exceptions
        try {
            weather = mapper.readValue(weatherInfoJson, Weather.class);
            weather.setCity(city);
            weather.updateCurrentConditions();
            weather.setTimestamp(new Date());
            weatherRepository.save(weather);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return weather; // Return weather object
        }
    }
}
