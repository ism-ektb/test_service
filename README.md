## REST API сервис для приёма данных от сенсора

В проекте две папки 
- service - сервис для регистрации сенсора и сохранения сообщений от него
- client - клиент эмитирующий работу сенсора

Для сборки и запуска проекта необходима среда разработки и Docker. Интеграционные тесты в проекте используют Testcontainers для работы которого необходим Docker.

Для запуска сервера в среде Docker на локальном компьютере необходимо перейти в папку "service" и собрать проект командой "mvn package"
Затем в среде разработки запустить находящийся в корневой папке "docker-compose.yml" который создаст контейнер с БД и контейнер с сервером и запустит их в среде Docker. 

### swagger
Для проверки работоспособности после запуска проекта в браузере нужно открыть http://127.0.0.1:8080/swagger-ui/index.html
В пункте "аутентификация" пройти регистрацию и получить JWT-токен, который необходимо сохранить в меню "Authorize".

### client
Для проверки работоспособности сервиса создан клиент. Он находится в папке client/.
Тестировать клиент можно только призапущенном сервере.
В среде разработки необходимо запустить тест client/src/test/java/ru/ism/client/ClientTest.java
Вначале тест зарегистрирует sensor в сервис работающий на локальном компьютере. Затем передаст 1000 случайных измерений, затем проверит число полученныз сервисом измерений.
Второй тест попробует зарегистрировать сенсор повторно и получил ошибку.

При возникновении вопросов можно написать в телеграмм @ism_ek
