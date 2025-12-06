package com.daw.weatherinfoapp.app;

import com.daw.weatherinfoapp.domain.api.response.GeoApifyResponse;
import com.daw.weatherinfoapp.domain.api.response.OpenWeatherResponse;
import com.daw.weatherinfoapp.domain.services.GeoApifyService;
import com.daw.weatherinfoapp.domain.services.OpenWeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class WeatherInfoApp {

    @Autowired
    GeoApifyService geoApifyService;

    @Autowired
    OpenWeatherService openWeatherService;

    public OpenWeatherResponse getWeatherInfo(String aCity) throws IOException {
        GeoApifyResponse geoResponse = geoApifyService.geocoding(aCity);

        double temp = openWeatherService.getWeather(geoResponse.getLat(), geoResponse.getLon());

        return new OpenWeatherResponse(geoResponse.getLat(), geoResponse.getLon(), temp);
    }
}