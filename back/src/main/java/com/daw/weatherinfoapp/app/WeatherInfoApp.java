package com.daw.weatherinfoapp.app;

import com.daw.weatherinfoapp.domain.api.response.GeoApifyResponse;
import com.daw.weatherinfoapp.domain.services.GeoApifyService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@AllArgsConstructor
public class WeatherInfoApp {

    GeoApifyService geoApifyService;
    //OpenWeatherService openWeatherService;

    public GeoApifyResponse getWeatherInfo(String aCity) throws IOException {
        GeoApifyResponse response = geoApifyService.geocoding(aCity);
        return response;
    }
}
