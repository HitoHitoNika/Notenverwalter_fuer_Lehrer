package de.hitohitonika.data.entities;

import jakarta.persistence.*;

@Entity
@Table(name="Classes")
public class SchoolClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "class_id")
    private int id;

    @Column(name = "Name")
    private String name;

    public SchoolClass() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
