package com.daw.weatherinfoapp.infra.controllers;

import com.daw.weatherinfoapp.app.WeatherInfoApp;
import com.daw.weatherinfoapp.domain.api.response.OpenWeatherResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class WeatherInfoController {

    @Autowired
    WeatherInfoApp app;

    @GetMapping("/getLatLon")
    public OpenWeatherResponse getLatLon(@RequestParam(name="city") String city) throws IOException {
        return app.getWeatherInfo(city);
    }
}
