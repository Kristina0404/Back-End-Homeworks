package de.ait.events.models;

import lombok.*;

import javax.persistence.*;
import java.util.Optional;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@ToString
public class Venue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 100)
    private String nameOfVenue;
    @Column(nullable = false, length = 100)
    private String address;
    @Column(nullable = false, length = 20)
    private String phone;

    @OneToMany(mappedBy = "venue")
    @EqualsAndHashCode.Exclude
    private Set<Event> events;


}
