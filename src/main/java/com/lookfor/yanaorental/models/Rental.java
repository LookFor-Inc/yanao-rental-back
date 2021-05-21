package com.lookfor.yanaorental.models;

import com.lookfor.yanaorental.models.equipment.Equipment;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.net.URL;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "rentals")
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 128)
    private String name;

    @Column(nullable = false, length = 512)
    private String address;

    @Column(length = 128)
    private URL img;

    @Column
    private Double latitude;

    @Column
    private Double longitude;

    @OneToMany(mappedBy = "rental", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Equipment> equipments;

    @OneToMany(mappedBy = "rental")
    private List<Reservation> reservations;
}
