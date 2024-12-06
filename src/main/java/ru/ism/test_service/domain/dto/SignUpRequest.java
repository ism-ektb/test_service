package ru.ism.test_service.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Запрос на регистрацию")
public class SignUpRequest {

    @Schema(description = "Логин", example = "Jon")
    @NotBlank(message = "Имя пользователя не может быть пустыми")
    private String name;



}