package de.hitohitonika.data.entities;

import jakarta.persistence.*;

@Entity
@Table(name="Subjects")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}
