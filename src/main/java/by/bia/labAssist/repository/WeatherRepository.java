package by.bia.labAssist.repository;

import by.bia.labAssist.model.Weather;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface WeatherRepository extends JpaRepository<Weather, Integer> {

    List<Weather> findAllByDateBetween(LocalDate startDate, LocalDate endDate);
}
