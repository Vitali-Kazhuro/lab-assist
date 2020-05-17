package by.bia.labAssist.service;

import by.bia.labAssist.model.Weather;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface WeatherService {
    List<Weather> findAll();

    Page<Weather> findAllPages(Pageable pageable);

    Weather findById(Integer id);

    void create(String date, Float k53_10_temperature, Float k53_16_temperature, Float k42_10_temperature,
                Float k42_16_temperature, Float k53_10_humidity, Float k53_16_humidity, Float k42_10_humidity,
                Float k42_16_humidity, Integer k53_10_pressure, Integer k53_16_pressure, Integer k42_10_pressure,
                Integer k42_16_pressure);

    void edit(Weather editWeather, String date, Float k53_10_temperature, Float k53_16_temperature, Float k42_10_temperature,
              Float k42_16_temperature, Float k53_10_humidity, Float k53_16_humidity, Float k42_10_humidity,
              Float k42_16_humidity, Integer k53_10_pressure, Integer k53_16_pressure, Integer k42_10_pressure,
              Integer k42_16_pressure);

    void delete(Integer id);

    String getWeatherPassage(LocalDate startDate, LocalDate endDate);
}
