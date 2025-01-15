package ru.ism.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import ru.ism.client.model.JwtAuthenticationResponse;
import ru.ism.client.model.SignUpRequest;
import ru.ism.client.model.Token;
import ru.ism.client.model.WeatherDto;

import java.util.List;

@FeignClient(value = "client", url = "http://localhost:8080")
public interface Client {
    @PostMapping("/sensors/registration")
    JwtAuthenticationResponse registration(SignUpRequest signUpRequest);

    @PostMapping("/measurements/add")
    WeatherDto addWeather(@RequestHeader("Authorization") String bearerToken,
                          @RequestBody WeatherDto weatherDto);
    @GetMapping("/measurements")
    List<WeatherDto> getAll(@RequestHeader("Authorization") String bearerToken);
}
