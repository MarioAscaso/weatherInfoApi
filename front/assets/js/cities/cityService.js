const GET_LAT_LON = "http://localhost:8080/api/getLatLon?";

function getCities() {
    return ["Madrid", "Barcelona", "Zaragoza", "Las Palmas", "Lugo"];
}

function getLongitudeLatitude(aCity) {
    const param = "city=" + aCity;
    return get(GET_LAT_LON + param);
}

//set interval...