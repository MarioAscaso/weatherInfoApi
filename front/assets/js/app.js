document.addEventListener('DOMContentLoaded', () => {
    const cities = getCities();
    const citiesSelect = document.getElementById("cities");
    const buttons = document.getElementsByTagName("button");
    const buttonSubmit = buttons[0];

    const resultDiv = document.getElementById("weatherResult");

    const REFRESH_TIME_MS = 5 * 60 * 1000;
    const LABEL_UPDATE_MS = 60 * 1000;

    let autoRefreshInterval = null;
    let labelUpdateInterval = null;
    let lastUpdateTimestamp = null;

    populateSelect(citiesSelect, cities);

    function populateSelect(select, items) {
        items.forEach(item => {
            const option = document.createElement("option");
            option.text = item;
            option.value = item;
            select.appendChild(option);
        });
    }

    async function updateWeatherInfo(city) {
        console.log(`Buscando datos para: ${city}...`);
        try {
            const response = await getLongitudeLatitude(city);
            if (response && response.temp !== undefined) {
                lastUpdateTimestamp = new Date();
                let htmlContent = `<div>La temperatura en ${city} es de ${response.temp}ºC</div>`;
                if (response.icon) {
                    const iconUrl = `https://openweathermap.org/img/wn/${response.icon}@2x.png`;
                    htmlContent += `<img src="${iconUrl}" alt="Icono tiempo" style="vertical-align: middle;">`;
                }
                htmlContent += `<div id="lastUpdatedMsg" style="font-size: 0.85rem; color: #666; margin-top: 10px;">Actualizado hace menos de 1 minuto</div>`;
                resultDiv.innerHTML = htmlContent;
                startLabelUpdater();
            } else {
                resultDiv.innerText = "No se pudo obtener la temperatura.";
                stopLabelUpdater();
            }
        } catch (error) {
            console.error(error);
            resultDiv.innerText = "Error al conectar con el servicio.";
            stopLabelUpdater();
        }
    }

    function updateTimeLabel() {
        if (!lastUpdateTimestamp) return;
        const now = new Date();
        const diffMs = now - lastUpdateTimestamp;
        const diffMins = Math.floor(diffMs / 60000);
        const msgElement = document.getElementById("lastUpdatedMsg");
        if (msgElement) {
            if (diffMins < 1) {
                msgElement.innerText = "Actualizado hace menos de 1 minuto";
            } else {
                msgElement.innerText = `Actualizado hace ${diffMins} minutos`;
            }
        }
    }

    function startLabelUpdater() {
        if (labelUpdateInterval) clearInterval(labelUpdateInterval);
        labelUpdateInterval = setInterval(updateTimeLabel, LABEL_UPDATE_MS);
    }

    function stopLabelUpdater() {
        if (labelUpdateInterval) clearInterval(labelUpdateInterval);
        lastUpdateTimestamp = null;
    }

    function startAutoRefresh(city) {
        if (autoRefreshInterval) clearInterval(autoRefreshInterval);
        autoRefreshInterval = setInterval(() => {
            updateWeatherInfo(city);
        }, REFRESH_TIME_MS);
    }

    buttonSubmit.addEventListener("click", async (event) => {
        event.preventDefault();
        const selectedCity = citiesSelect.value;
        await updateWeatherInfo(selectedCity);
        startAutoRefresh(selectedCity);
    });
});