package ru.ism.test_service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.ism.test_service.config.SecurityConfiguration;
import ru.ism.test_service.domain.dto.JwtAuthenticationResponse;
import ru.ism.test_service.domain.dto.SignUpRequest;
import ru.ism.test_service.service.AuthenticationService;
import ru.ism.test_service.service.JwtService;
import ru.ism.test_service.service.UserService;

import java.nio.charset.StandardCharsets;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Юнит тестирование контроллера регистрации
 */

@WebMvcTest(value = AuthController.class)
@Import(SecurityConfiguration.class)
class AuthControllerTest {

    @MockBean
    private JwtService jwtService;

    @MockBean
    private UserService userService;

    @MockBean
    private AuthenticationService authenticationService;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private MockMvc mvc;

    @Test
    @WithAnonymousUser
    @SneakyThrows
    void signUp_isOk() {
        SignUpRequest signUpRequest = SignUpRequest.builder().name("sensor").build();
        when(authenticationService.signUp(any())).thenReturn(JwtAuthenticationResponse.builder().build());

        mvc.perform(post("/sensors/registration")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(StandardCharsets.UTF_8)
                .content(mapper.writeValueAsString(signUpRequest))
        ).andExpect(status().isOk());
    }

    @Test
    @WithAnonymousUser
    @SneakyThrows
    void signUp_nameIsEmpty() {
        SignUpRequest signUpRequest = SignUpRequest.builder().name("").build();
        when(authenticationService.signUp(any())).thenReturn(JwtAuthenticationResponse.builder().build());

        mvc.perform(post("/sensors/registration")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(StandardCharsets.UTF_8)
                .content(mapper.writeValueAsString(signUpRequest))
        ).andExpect(status().isBadRequest());
    }
}