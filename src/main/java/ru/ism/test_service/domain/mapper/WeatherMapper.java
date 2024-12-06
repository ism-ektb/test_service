package ru.ism.test_service.domain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.ism.test_service.domain.dto.WeatherDto;
import ru.ism.test_service.domain.model.Weather;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface WeatherMapper {
    @Mapping(source = "weatherDto.sensor", target = "user")
    Weather dtoToModel(WeatherDto weatherDto);

    @Mapping(source = "weather.user", target = "sensor")
    WeatherDto modelToDto(Weather weather);
}
