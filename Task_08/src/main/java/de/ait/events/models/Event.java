package de.ait.events.models;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = "venue")
@Builder
@Entity

public class Event {

    @javax.persistence.Id
    //@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 20)
    private String title;
    @Column(nullable = false, length = 10)
    private LocalDate startDate;
    @Column(nullable = false, length = 10)
    private LocalDate endDate;

    @ManyToMany(mappedBy = "events")
    private Set<User> users;

    @ManyToOne
    @JoinColumn(name = "venue_id", nullable = false)
    private Venue venue;


}
