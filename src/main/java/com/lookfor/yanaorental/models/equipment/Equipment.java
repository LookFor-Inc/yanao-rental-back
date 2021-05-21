package com.lookfor.yanaorental.models.equipment;

import com.lookfor.yanaorental.models.rental.Rental;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.net.URL;

@Getter
@Setter
@Entity
@Table(name = "equipments")
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private URL img;

    @Column(nullable = false)
    private Long totalCount;

    @Column(nullable = false)
    private Long freeCount;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private EquipmentType type;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Rental rental;
}
