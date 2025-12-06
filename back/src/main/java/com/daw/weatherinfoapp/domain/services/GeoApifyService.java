package com.daw.weatherinfoapp.domain.services;

import com.daw.weatherinfoapp.domain.api.response.GeoApifyResponse;

import com.daw.weatherinfoapp.shared.api.ApiService;
import lombok.RequiredArgsConstructor;
import okhttp3.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class GeoApifyService {


    @Value("${geoapify.api.geocoding}")
    String geocodingEndpoint;

    @Autowired
    ApiService apiService;

    public GeoApifyResponse geocoding(String aCity) throws IOException {
        String geocodingEndpointUrl = geocodingEndpoint.replace("<city>", aCity);
        ResponseBody responseBody = apiService.get(geocodingEndpointUrl);

        if (responseBody == null) {
            throw new IOException("La respuesta de la API es nula");
        }

        String jsonString = responseBody.string();

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(jsonString);

        JsonNode results = rootNode.path("results");

        if (results.isArray() && results.size() > 0) {
            JsonNode firstResult = results.get(0);

            double lat = firstResult.path("lat").asDouble();
            double lon = firstResult.path("lon").asDouble();

            return new GeoApifyResponse(lon, lat);
        } else {
            throw new IOException("No se han encontrado coordenadas para la ciudad: " + aCity);
        }
    }
}
