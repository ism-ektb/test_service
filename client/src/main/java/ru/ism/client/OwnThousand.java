package ru.ism.client;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.ism.client.model.UserDto;
import ru.ism.client.model.WeatherDto;

/**
 * метод для передачи одной тысячи случайных сообщений о погоде
 */

@Component
public class OwnThousand {
    @Autowired
    private Client client;

    void send(String token, String sensorName) {
        for (int i = 0; i < 1000; i++) {
            int random = (int) (Math.random() * 100 - 50);
            float value = (float) random / 10;
            Boolean raining = random > 0 ? true : false;
            WeatherDto weatherDto = WeatherDto.builder().value(value)
                    .raining(raining)
                    .sensor(UserDto.builder().name(sensorName).build()).build();
            client.addWeather("Bearer " + token, weatherDto);

        }

    }


}
