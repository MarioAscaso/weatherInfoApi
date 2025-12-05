document.addEventListener('DOMContentLoaded', () => {
    const cities = getCities();
    const citiesSelect = document.getElementById("cities");
    const buttons = document.getElementsByTagName("button");
    const buttonSubmit = buttons[0];

    populateSelect(citiesSelect, cities);

    function populateSelect(select, items) {
        items.forEach(item => {
            const option = document.createElement("option");
                option.text = item;
                option.value = item;
            select.appendChild(option);
            console.log(select);
        })
    }

    citiesSelect.addEventListener("change", () => {
        const selectedCity = citiesSelect.value;
        getLongitudeLatitude(selectedCity);
    })



})