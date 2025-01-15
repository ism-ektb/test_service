package ru.ism.test_service.domain.mapper;

import org.mapstruct.Qualifier;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Интерфейс необхобибый для корректного мапинга зашифрованной информации
 * в упрощенном проекте не используется
 */

@Qualifier // org.mapstruct.Qualifier
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.CLASS)
public @interface EncodedMapping {
}
