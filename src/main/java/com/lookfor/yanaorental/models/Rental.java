package com.lookfor.yanaorental.models;

import com.lookfor.yanaorental.models.equipment.Equipment;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "rentals")
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 128)
    private String name;

    @Column(nullable = false, length = 128)
    private String town;

    @OneToMany(mappedBy = "rental", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Equipment> equipments;
}
