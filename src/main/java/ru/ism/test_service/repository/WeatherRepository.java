package ru.ism.test_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ism.test_service.domain.model.Weather;

import java.util.List;
import java.util.Optional;

public interface WeatherRepository extends JpaRepository <Weather, Long> {
    List<Weather> findWeatherByUserId(Long userId);
    List<Weather> findWeatherByRainingAndUserId(Boolean raining, Long userId);

}
