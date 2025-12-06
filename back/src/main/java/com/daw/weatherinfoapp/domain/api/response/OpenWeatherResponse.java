package com.daw.weatherinfoapp.domain.api.response;

public class OpenWeatherResponse {

    private double lat;
    private double lon;
    private double temp;

    public OpenWeatherResponse(double lat, double lon, double temp) {
        this.lat = lat;
        this.lon = lon;
        this.temp = temp;
    }

    public double getLat() { return lat; }
    public double getLon() { return lon; }
    public double getTemp() { return temp; }
}