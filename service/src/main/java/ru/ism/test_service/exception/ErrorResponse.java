package ru.ism.test_service.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * класс для передачи информации об ошибках REST-контроллера
 */

@Getter
@RequiredArgsConstructor
public class ErrorResponse {

    private final List<Error> errors;
}

