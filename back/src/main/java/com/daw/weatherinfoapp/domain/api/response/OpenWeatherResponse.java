package com.daw.weatherinfoapp.domain.api.response;

public class OpenWeatherResponse {

    private double lat;
    private double lon;
    private double temp;
    private String icon;

    public OpenWeatherResponse(double lat, double lon, double temp, String icon) {
        this.lat = lat;
        this.lon = lon;
        this.temp = temp;
        this.icon = icon;
    }

    public double getLat() { return lat; }
    public double getLon() { return lon; }
    public double getTemp() { return temp; }
    public String getIcon(){return icon;}
}