package com.lookfor.yanaorental.models.rental;

import com.lookfor.yanaorental.models.Reservation;
import com.lookfor.yanaorental.models.equipment.Equipment;
import com.lookfor.yanaorental.models.user.User;
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

    @Column(nullable = false, precision = 6, scale = 2)
    private Double latitude;

    @Column(nullable = false, precision = 6, scale = 2)
    private Double longitude;

    @OneToMany(mappedBy = "rental", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WorkTime> workTimes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    @OneToMany(mappedBy = "rental", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Equipment> equipments;

    @OneToMany(mappedBy = "rental")
    private List<Reservation> reservations;
}
