package ru.ism.test_service.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO с именем сенсора. Используется как при записи данных погоды в БД
 * так и при возврате удачно сохнаненных данных
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Ответ c токеном доступа")
public class UserDto {

    @Schema(description = "Логин", example = "sensorName")
    private String name;


}