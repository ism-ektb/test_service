package ru.ism.client;

import feign.FeignException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.ism.client.model.SignUpRequest;
import ru.ism.client.model.WeatherDto;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Совместное тестирование работы сервера и клиента
 * перед запуском теста необходимо запустить сервер в который будут записываться данные
 */

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

        //регистрация сенсора на сервере
        SignUpRequest signUpRequest = SignUpRequest.builder().name(name).build();
        var response = client.registration(signUpRequest);
        //запись 1000 случайных данных о погоде
        ownThousand.send(response.getToken(), name);
        //проверка
        List<WeatherDto> list = client.getAll("Bearer " + response.getToken());
        assertEquals(list.size(), 1000);

    }

    /**
     * проверка, что повторная регистрация сенсора с тем же именем не возможна
     */
    @Test
    @Order(2)
    void doubleRegistration_error() {
        SignUpRequest signUpRequest = SignUpRequest.builder().name(name).build();
        assertThrows(FeignException.class, () -> client.registration(signUpRequest));
    }
}