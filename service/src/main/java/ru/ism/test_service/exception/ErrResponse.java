package ru.ism.test_service.exception;

import lombok.Getter;

/**
 * класс для передачи информации об ошибках REST-контроллера
 */

@Getter
public class ErrResponse {
    private final String error;

    public ErrResponse(String error) {
        this.error = error;
    }

}
