package de.ait.events.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Optional;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
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
    private Set<Event> events;


}
