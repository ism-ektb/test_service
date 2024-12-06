package ru.ism.client;

import feign.FeignException;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.ism.client.model.SignUpRequest;
import ru.ism.client.model.UserDto;
import ru.ism.client.model.WeatherDto;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ClientTest {
    @Autowired
    private Client client;
    @Autowired
    private OwnThousand ownThousand;
    private String name = "sensorTest15";

    @Test
    @Order(1)
    void registration() {

        SignUpRequest signUpRequest = SignUpRequest.builder().name(name).build();
        var response = client.registration(signUpRequest);
        ownThousand.send(response.getToken(), name);

        List<WeatherDto> list = client.getAll("Bearer " + response.getToken());
        assertEquals(list.size(), 1000);

    }

    @Test
    @Order(2)
    void doubleRegistration(){
        SignUpRequest signUpRequest = SignUpRequest.builder().name(name).build();
        assertThrows(FeignException.class, () -> client.registration(signUpRequest));
    }
}