package ru.ism.test_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ism.test_service.domain.model.Weather;

import java.util.List;

/**
 * Интерфейс для хранения информации о погоде
 */

public interface WeatherRepository extends JpaRepository <Weather, Long> {
    List<Weather> findWeatherByUserId(Long userId);
    List<Weather> findWeatherByRainingAndUserId(Boolean raining, Long userId);

}
