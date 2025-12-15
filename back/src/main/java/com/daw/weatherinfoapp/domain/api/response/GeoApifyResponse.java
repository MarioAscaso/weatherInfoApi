package com.daw.weatherinfoapp.domain.api.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GeoApifyResponse {
    private double lon;
    private double lat;

    public GeoApifyResponse(double lon, double lat) {
        this.lon = lon;
        this.lat = lat;
    }

    public double getLon() {return lon;}
    public double getLat() {return lat;}
}
