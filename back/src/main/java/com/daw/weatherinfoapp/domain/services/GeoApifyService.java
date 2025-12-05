package com.daw.weatherinfoapp.domain.services;

import com.daw.weatherinfoapp.domain.api.response.GeoApifyResponse;

import com.daw.weatherinfoapp.shared.api.ApiService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.AbstractMap;

@Service
@RequiredArgsConstructor
public class GeoApifyService {


    @Value("${geoapify.api.geocoding}")
    String geocodingEndpoint;

    @Autowired
    ApiService apiService;

    public GeoApifyResponse geocoding(String aCity) throws IOException {
        String geocodingEndpointUrl = geocodingEndpoint.replace("<city>", aCity);
        ResponseBody response = apiService.get(geocodingEndpointUrl);
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.readValue(response.string(), GeoApifyResponse.class);
    }




}
