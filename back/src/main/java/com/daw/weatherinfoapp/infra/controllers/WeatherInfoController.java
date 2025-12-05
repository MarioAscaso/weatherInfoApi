package com.daw.weatherinfoapp.infra.controllers;

import com.daw.weatherinfoapp.app.WeatherInfoApp;
import com.daw.weatherinfoapp.domain.api.response.GeoApifyResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class WeatherInfoController {

    WeatherInfoApp app;

    @GetMapping("/getLatLon")
    public GeoApifyResponse getLatLon(@RequestParam(name="city") String city) throws IOException {
        return app.getWeatherInfo(city);
    }
}
