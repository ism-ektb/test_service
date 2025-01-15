package ru.ism.test_service.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.ism.test_service.domain.dto.WeatherDto;
import ru.ism.test_service.domain.mapper.WeatherListMapper;
import ru.ism.test_service.domain.mapper.WeatherMapper;
import ru.ism.test_service.domain.model.Weather;
import ru.ism.test_service.service.UserService;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/measurements")
@RequiredArgsConstructor
@Validated
@Tag(name = "Работа с учетной записью", description = "Доступен только авторизованным пользователям")
public class WeatherController {
    private final UserService service;
    private final WeatherListMapper listMapper;
    private final WeatherMapper weatherMapper;

    @PostMapping("/add")
    @Operation(summary = "Добавление кванта новых данных")
    @SecurityRequirement(name = "JWT")
    public WeatherDto addWeather(@RequestBody WeatherDto weatherDto) {
        Weather weather = service.saveWeather(weatherMapper.dtoToModel(weatherDto));
        log.info("успешно добавлено значение с id= {} от сенсора {}", weather.getId(), weather.getUser().getUsername());
        return weatherMapper.modelToDto(weather);
    }

    @GetMapping
    @Operation(summary = "получение всех данных")
    @SecurityRequirement(name = "JWT")
    public List<WeatherDto> findAll() {
        List<Weather> weathers = service.findAllWeather();
        log.info("Получен список из {} измерений погоды", weathers.size());
        return listMapper.modelsToDtos(weathers);
    }

    @GetMapping("/rainyDaysCount")
    @Operation(summary = "Получение числа дождливых дней")
    @SecurityRequirement(name = "JWT")
    public Integer findCountRainingDays() {
        Integer count = service.countRainingDay();
        log.info("Число дождливых дней {}", count);
        return count;
    }


}