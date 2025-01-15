package ru.ism.test_service.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * Класс для передачи информации об ошибках произошедших при работе контроллера
 */

@Getter
@RequiredArgsConstructor
@ToString
public class Error {
    private final String reason;
    private final String message;
}
