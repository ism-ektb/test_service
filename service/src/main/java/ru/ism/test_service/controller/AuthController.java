package ru.ism.test_service.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ism.test_service.domain.dto.JwtAuthenticationResponse;
import ru.ism.test_service.domain.dto.SignInRequest;
import ru.ism.test_service.domain.dto.SignUpRequest;
import ru.ism.test_service.service.AuthenticationService;

@RestController
@RequestMapping("/sensors")
@RequiredArgsConstructor
@Tag(name = "Аутентификация")
public class AuthController {
    private final AuthenticationService authenticationService;

    /**
     * Метод регистрации сенсора
     * @return JWT - токен необходимый для дальнейшей работы
     */
    @Operation(summary = "Регистрация пользователя")
    @PostMapping("/registration")
    public JwtAuthenticationResponse signUp(@RequestBody @Valid SignUpRequest request) {
        return authenticationService.signUp(request);
    }

}

