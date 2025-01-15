package ru.ism.test_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ism.test_service.domain.model.User;

import java.util.Optional;

/**
 * интерфейс для хранения информации о сенсоре в БД
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);


}