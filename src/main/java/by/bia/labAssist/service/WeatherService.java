package by.bia.labAssist.service;

import by.bia.labAssist.model.Weather;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

/**
 * Provides service logic for{@link Weather} entity
 */
public interface WeatherService {
    /**
     * Returns list with all Weather instances
     * @return {@link List<Weather>} object
     */
    List<Weather> findAll();

    /**
     * Returns page of all Weather instances
     * @param pageable pageable object
     * @return {@link Page<Weather>} object
     */
    Page<Weather> findAllPages(Pageable pageable);

    /**
     * Returns Weather instance by id
     * @param id Weather id
     * @return {@link Weather} object
     */
    Weather findById(Integer id);

    /**
     * Creates new Weather instance and persists it into database
     * @param date Weather date
     * @param k53_10_temperature Weather temperature in lab 53 at 10 a.m.
     * @param k53_16_temperature Weather temperature in lab 53 at 16 p.m.
     * @param k42_10_temperature Weather temperature in lab 42 at 10 a.m.
     * @param k42_16_temperature Weather temperature in lab 42 at 16 a.m.
     * @param k53_10_humidity Weather humidity in lab 53 at 10 a.m.
     * @param k53_16_humidity Weather humidity in lab 53 at 16 p.m.
     * @param k42_10_humidity Weather humidity in lab 42 at 10 a.m.
     * @param k42_16_humidity Weather humidity in lab 42 at 16 a.m.
     * @param k53_10_pressure Weather pressure in lab 53 at 10 a.m.
     * @param k53_16_pressure Weather pressure in lab 53 at 16 p.m.
     * @param k42_10_pressure Weather pressure in lab 42 at 10 a.m.
     * @param k42_16_pressure Weather pressure in lab 42 at 16 a.m.
     */
    void create(String date, Float k53_10_temperature, Float k53_16_temperature, Float k42_10_temperature,
                Float k42_16_temperature, Float k53_10_humidity, Float k53_16_humidity, Float k42_10_humidity,
                Float k42_16_humidity, Integer k53_10_pressure, Integer k53_16_pressure, Integer k42_10_pressure,
                Integer k42_16_pressure);

    /**
     * Edits passed Weather instance and persists it into database
     * @param editWeather edited instance of Weather
     * @param date Weather date
     * @param k53_10_temperature Weather temperature in lab 53 at 10 a.m.
     * @param k53_16_temperature Weather temperature in lab 53 at 16 p.m.
     * @param k42_10_temperature Weather temperature in lab 42 at 10 a.m.
     * @param k42_16_temperature Weather temperature in lab 42 at 16 a.m.
     * @param k53_10_humidity Weather humidity in lab 53 at 10 a.m.
     * @param k53_16_humidity Weather humidity in lab 53 at 16 p.m.
     * @param k42_10_humidity Weather humidity in lab 42 at 10 a.m.
     * @param k42_16_humidity Weather humidity in lab 42 at 16 a.m.
     * @param k53_10_pressure Weather pressure in lab 53 at 10 a.m.
     * @param k53_16_pressure Weather pressure in lab 53 at 16 p.m.
     * @param k42_10_pressure Weather pressure in lab 42 at 10 a.m.
     * @param k42_16_pressure Weather pressure in lab 42 at 16 a.m.
     */
    void edit(Weather editWeather, String date, Float k53_10_temperature, Float k53_16_temperature, Float k42_10_temperature,
              Float k42_16_temperature, Float k53_10_humidity, Float k53_16_humidity, Float k42_10_humidity,
              Float k42_16_humidity, Integer k53_10_pressure, Integer k53_16_pressure, Integer k42_10_pressure,
              Integer k42_16_pressure);

    /**
     * Deletes instance of Weather from database
     * @param id Weather id
     */
    void delete(Integer id);

    /**
     * Creates weather passage for {@link by.bia.labAssist.model.TestReport}
     * @param startDate corresponds with {@link by.bia.labAssist.model.TestReport} startDate
     * @param endDate corresponds with {@link by.bia.labAssist.model.TestReport} startDate
     * @return {@link String} with created passage
     */
    String createWeatherPassage(LocalDate startDate, LocalDate endDate);
}
