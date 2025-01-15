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
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.ism.test_service.config.SecurityConfiguration;
import ru.ism.test_service.domain.dto.UserDto;
import ru.ism.test_service.domain.dto.WeatherDto;
import ru.ism.test_service.domain.mapper.WeatherListMapper;
import ru.ism.test_service.domain.mapper.WeatherMapper;
import ru.ism.test_service.domain.model.User;
import ru.ism.test_service.domain.model.Weather;
import ru.ism.test_service.service.AuthenticationService;
import ru.ism.test_service.service.JwtService;
import ru.ism.test_service.service.UserService;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Юнит тестирование контроллера записи измерений
 */

@WebMvcTest(value = WeatherController.class)
@Import(SecurityConfiguration.class)
class WeatherControllerTest {

    @MockBean
    private JwtService jwtService;

    @MockBean
    private UserService userService;

    @MockBean
    private AuthenticationService authenticationService;

    @MockBean
    private WeatherMapper weatherMapper;

    @MockBean
    private WeatherListMapper weatherListMapper;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private MockMvc mvc;


    @Test
    @SneakyThrows
    @WithMockUser
    void addWeather() {
        WeatherDto weatherDto = WeatherDto.builder().value(23.3F)
                .raining(true)
                .sensor(UserDto.builder().name("sensor").build()).build();
        when(userService.saveWeather(any())).thenReturn(Weather.builder().user(User.builder().build()).build());
        when(weatherMapper.dtoToModel(any())).thenReturn(Weather.builder().build());
        when(weatherMapper.modelToDto(any())).thenReturn(WeatherDto.builder().build());
        mvc.perform(post("/measurements/add")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(StandardCharsets.UTF_8)
                .content(mapper.writeValueAsString(weatherDto)))
                .andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    @WithAnonymousUser
    void addWeather_anonymous() {
        WeatherDto weatherDto = WeatherDto.builder().value(23.3F)
                .raining(true)
                .sensor(UserDto.builder().name("sensor").build()).build();
        when(userService.saveWeather(any())).thenReturn(Weather.builder().user(User.builder().build()).build());
        when(weatherMapper.dtoToModel(any())).thenReturn(Weather.builder().build());
        when(weatherMapper.modelToDto(any())).thenReturn(WeatherDto.builder().build());
        mvc.perform(post("/measurements/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .content(mapper.writeValueAsString(weatherDto)))
                .andExpect(status().is(403));
    }

    @Test
    @SneakyThrows
    @WithMockUser
    void findAll() {
        when(userService.findAllWeather()).thenReturn(List.of(Weather.builder().build()));
        mvc.perform(get("/measurements")).andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    @WithAnonymousUser
    void findAll_anonymous() {
        when(userService.findAllWeather()).thenReturn(List.of(Weather.builder().build()));
        mvc.perform(get("/measurements")).andExpect(status().is(403));
    }

    @Test
    @SneakyThrows
    @WithMockUser
    void findCountRainingDays() {
        when(userService.countRainingDay()).thenReturn(1);
        mvc.perform(get("/measurements/rainyDaysCount")).andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    @WithAnonymousUser
    void findCountRainingDays_anonymous() {
        when(userService.countRainingDay()).thenReturn(1);
        mvc.perform(get("/measurements/rainyDaysCount")).andExpect(status().is(403));
    }
}