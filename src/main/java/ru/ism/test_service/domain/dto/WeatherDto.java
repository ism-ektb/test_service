package ru.ism.test_service.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
@Schema(description = "Входящая информация от сенсора")
public class WeatherDto {
    @Schema(name = "температура", example = "23.1")
    @NotNull(message = "Значение value не должно быть пустым")
    private Float value;
    @Schema(name = "Наличие дождя", example = "true")
    @NotNull(message = "Значение raining не должно быть пустым")
    private Boolean raining;
    @Schema(name = "sensor", example = "sensor")
    private UserDto sensor;

}
