package com.daw.weatherinfoapp.domain.services;

import com.daw.weatherinfoapp.shared.api.ApiService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import okhttp3.ResponseBody;

import java.io.IOException;

@Service
public class OpenWeatherService {

    @Value("${openweather.api.current_weather}")
    private String weatherEndpoint;

    @Autowired
    private ApiService apiService;

    public double getWeather(double lat, double lon) throws IOException {
        String url = weatherEndpoint
                .replace("<lat>", String.valueOf(lat))
                .replace("<lon>", String.valueOf(lon));

        ResponseBody responseBody = apiService.get(url);
        if (responseBody == null) {
            throw new IOException("La respuesta de OpenWeather es nula");
        }

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(responseBody.string());

        if (rootNode.has("main") && rootNode.path("main").has("temp")) {
            return rootNode.path("main").path("temp").asDouble();
        } else {
            throw new IOException("No se ha encontrado la temperatura en la respuesta de OpenWeather");
        }
    }
}