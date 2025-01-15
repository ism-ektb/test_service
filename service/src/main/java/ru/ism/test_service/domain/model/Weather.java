package ru.ism.test_service.domain.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * класс для хранения данных о погоде передаваемой сенсором в БД
 */

@Entity
@Table(name = "weather")
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode
public class Weather {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
    @Column(name = "temperature")
    private Float value;
    private Boolean raining;

}
