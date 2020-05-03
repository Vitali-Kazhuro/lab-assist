package by.bia.labAssist.service.impl;

import by.bia.labAssist.service.WeatherService;
import by.bia.labAssist.model.Weather;
import by.bia.labAssist.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WeatherServiceImpl implements WeatherService {
    @Autowired
    private WeatherRepository weatherRepository;

    @Override
    public void save(String date, Float k53_10_temperature, Float k53_16_temperature, Float k42_10_temperature,
                     Float k42_16_temperature, Float k53_10_humidity, Float k53_16_humidity, Float k42_10_humidity,
                     Float k42_16_humidity, Integer k53_10_pressure, Integer k53_16_pressure, Integer k42_10_pressure,
                     Integer k42_16_pressure) {
        LocalDate formattedDate = LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);

        Weather weather = new Weather(formattedDate, k53_10_temperature, k53_16_temperature, k42_10_temperature,
                k42_16_temperature, k53_10_humidity, k53_16_humidity, k42_10_humidity, k42_16_humidity,
                k53_10_pressure, k53_16_pressure, k42_10_pressure, k42_16_pressure);

        weatherRepository.save(weather);

    }

    @Override
    public void edit(Weather editWeather, String date, Float k53_10_temperature, Float k53_16_temperature,
                     Float k42_10_temperature, Float k42_16_temperature, Float k53_10_humidity, Float k53_16_humidity,
                     Float k42_10_humidity, Float k42_16_humidity, Integer k53_10_pressure, Integer k53_16_pressure,
                     Integer k42_10_pressure, Integer k42_16_pressure) {
        LocalDate formattedDate = LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);

        editWeather.setDate(formattedDate);
        editWeather.setK53_10_temperature(k53_10_temperature);
        editWeather.setK53_16_temperature(k53_16_temperature);
        editWeather.setK42_10_temperature(k42_10_temperature);
        editWeather.setK42_16_temperature(k42_16_temperature);
        editWeather.setK53_10_humidity(k53_10_humidity);
        editWeather.setK53_16_humidity(k53_16_humidity);
        editWeather.setK42_10_humidity(k42_10_humidity);
        editWeather.setK42_16_humidity(k42_16_humidity);
        editWeather.setK53_10_pressure(k53_10_pressure);
        editWeather.setK53_16_pressure(k53_16_pressure);
        editWeather.setK42_10_pressure(k42_10_pressure);
        editWeather.setK42_16_pressure(k42_16_pressure);

        weatherRepository.save(editWeather);
    }

    @Override
    public String getWeatherPassage(LocalDate startDate, LocalDate endDate) {
        List<Weather> weatherList = weatherRepository.findAllByDateBetween(startDate, endDate);

        List<Float> temperatureList = weatherList.stream().map(Weather::getAllTemperature)
                .flatMap(List::stream).collect(Collectors.toList());
        List<Float> humidityList = weatherList.stream().map(Weather::getAllHumidity)
                .flatMap(List::stream).collect(Collectors.toList());
        List<Integer> pressureList = weatherList.stream().map(Weather::getAllPressure)
                .flatMap(List::stream).collect(Collectors.toList());
        Float minTemperature = temperatureList.stream().min(Float::compareTo).get();
        Float maxTemperature = temperatureList.stream().max(Float::compareTo).get();
        Float minHumidity = humidityList.stream().min(Float::compareTo).get();
        Float maxHumidity = humidityList.stream().max(Float::compareTo).get();
        Integer minPressure = pressureList.stream().min(Integer::compareTo).get();
        Integer maxPressure = pressureList.stream().max(Integer::compareTo).get();

        StringBuilder sb = new StringBuilder();
        sb.append("Температура воздуха ");
        if (minTemperature.equals(maxTemperature))
            sb.append(minTemperature.toString().replace(".", ",")).append(" °С, ");
        else
            sb.append(minTemperature.toString().replace(".", ",")).append("-")
                    .append(maxTemperature.toString().replace(".", ",")).append(" °С, ");
        sb.append("относительная влажность ");
        if (minHumidity.equals(maxHumidity))
            sb.append(minHumidity.toString().replace(".", ",")).append(" %, \n\t");
        else
            sb.append(minHumidity.toString().replace(".", ",")).append("-")
                    .append(maxHumidity.toString().replace(".", ",")).append(" %, \n\t");
        sb.append("атмосферное давление ");
        if (minPressure.equals(maxPressure))
            sb.append(minPressure).append(" мм рт. ст.");
        else
            sb.append(minPressure).append("-").append(maxPressure).append(" мм рт. ст.");

        return sb.toString();
    }

    @Override
    public void delete(Integer id) {
        weatherRepository.deleteById(id);
    }

    @Override
    public List<Weather> findAll() {
        return weatherRepository.findAll();
    }

    @Override
    public Page<Weather> findAllPages(Pageable pageable) {
        return weatherRepository.findAll(pageable);
    }

    @Override
    public Weather findById(Integer id) {
        return weatherRepository.findById(id).get();
    }
}
