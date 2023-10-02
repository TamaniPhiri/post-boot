package com.postBoot.postBoot.model;
import jakarta.persistence.*;

@Entity
@Table(name = "tutorials")
public class Tutorial {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
}
