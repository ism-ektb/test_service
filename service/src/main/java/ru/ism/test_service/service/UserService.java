package ru.ism.test_service.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ism.test_service.domain.model.User;
import ru.ism.test_service.domain.model.Weather;
import ru.ism.test_service.exception.exception.BaseRelationshipException;
import ru.ism.test_service.repository.UserRepository;
import ru.ism.test_service.repository.WeatherRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository repository;
    private final WeatherRepository weatherRepository;


    /**
     * Сохранение пользователя
     *
     * @return сохраненный пользователь
     */
    public User save(User user) {
        return repository.save(user);
    }


    /**
     * Создание пользователя
     *
     * @return созданный пользователь
     */
    public User create(User user) {
        if (repository.existsByUsername(user.getUsername())) {

            throw new BaseRelationshipException("Пользователь с таким именем уже существует");
        }

        User saveUser = save(user);


        return saveUser;
    }

    /**
     * Получение пользователя по имени пользователя
     *
     * @return пользователь
     */
    public User getByUsername(String username) {
        return repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));

    }

    /**
     * Получение пользователя по имени пользователя
     * <p>
     * Нужен для Spring Security
     *
     * @return пользователь
     */
    public UserDetailsService userDetailsService() {
        return this::getByUsername;
    }

    /**
     * Получение текущего пользователя
     *
     * @return текущий пользователь
     */
    public User getCurrentUser() {
        // Получение имени пользователя из контекста Spring Security
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return getByUsername(username);
    }

    /**
     * Метод для сохранения данных о погоде
     *
     * @return успешно сохраненнын данные о погоде
     */
    @Transactional
    public Weather saveWeather(Weather weather) {
        Optional<User> temp = repository.findByUsername(weather.getUser().getUsername());
        if (temp.isEmpty() || getCurrentUser().equals(temp)) throw new BaseRelationshipException("Ошибка авторизации");
        weather.setUser(temp.get());
        return weatherRepository.save(weather);
    }

    /**
     * метод для получения всех сохраненных данных о погоде от конкретного сенсора
     */
    public List<Weather> findAllWeather() {
        return weatherRepository.findWeatherByUserId(getCurrentUser().getId());
    }

    /**
     * получение числа дождливых дней
     * @return возвроащает число дождливых дней
     */
    @Transactional
    public Integer countRainingDay() {
        return weatherRepository.findWeatherByRainingAndUserId(true, getCurrentUser().getId()).size();
    }


}
