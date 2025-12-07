package com.daw.weatherinfoapp.domain.services;

import com.daw.weatherinfoapp.domain.api.response.OpenWeatherResponse;
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

    public OpenWeatherResponse getWeather(double lat, double lon) throws IOException {
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
            double temp = rootNode.path("main").path("temp").asDouble();

            String icon = "";
            if (rootNode.has("weather") && rootNode.path("weather").isArray()) {
                icon = rootNode.path("weather").get(0).path("icon").asText();
            }
            return new OpenWeatherResponse(lat, lon, temp, icon);
        } else {
            throw new IOException("No se ha encontrado la temperatura en la respuesta");
        }
    }
}