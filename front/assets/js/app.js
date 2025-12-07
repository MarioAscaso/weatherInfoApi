document.addEventListener('DOMContentLoaded', () => {
    const cities = getCities();
    const citiesSelect = document.getElementById("cities");
    const buttons = document.getElementsByTagName("button");
    const buttonSubmit = buttons[0];

    // --- ESTA ES LA LÍNEA QUE FALTA ---
    // Creamos la referencia al div que añadimos en el HTML
    const resultDiv = document.getElementById("weatherResult");
    // ----------------------------------

    populateSelect(citiesSelect, cities);

    function populateSelect(select, items) {
        items.forEach(item => {
            const option = document.createElement("option");
            option.text = item;
            option.value = item;
            select.appendChild(option);
        });
    }

    buttonSubmit.addEventListener("click", async (event) => {
            event.preventDefault();

            const selectedCity = citiesSelect.value;
            const response = await getLongitudeLatitude(selectedCity);
            console.log("Respuesta completa:", response);

            if (response && response.temp !== undefined) {
                // Preparamos el HTML con la temperatura
                let htmlContent = `La temperatura en ${selectedCity} es de ${response.temp}ºC `;

                // Si viene el icono, añadimos una imagen
                if (response.icon) {
                    const iconUrl = `https://openweathermap.org/img/wn/${response.icon}@2x.png`;
                    htmlContent += `<img src="${iconUrl}" alt="Icono tiempo" style="vertical-align: middle;">`;
                }

                resultDiv.innerHTML = htmlContent;
            } else {
                resultDiv.innerText = "No se pudo obtener la temperatura.";
            }
        });
});