package de.ait.ec.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Course{

    public enum State {
        DRAFT, PUBLISHED
    }

    @javax.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,length = 20)
    private String title;
    private LocalDate beginDate;
    private LocalDate endDate;
    @Column(nullable = false,length = 1000)
    private String description;
    @Column(nullable = false,length = 20)

    private double price;

    @Enumerated(value = EnumType.STRING)

    private State state;
}
