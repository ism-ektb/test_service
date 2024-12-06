package ru.ism.test_service.domain.mapper;

import org.mapstruct.Mapper;
import ru.ism.test_service.domain.dto.WeatherDto;
import ru.ism.test_service.domain.model.Weather;

import java.util.List;

@Mapper(componentModel = "spring", uses = WeatherMapper.class)
public interface WeatherListMapper {
    List<WeatherDto> modelsToDtos(List<Weather> weathers);
}
