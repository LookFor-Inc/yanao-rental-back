package com.lookfor.yanaorental.models.equipment;

import com.lookfor.yanaorental.models.Rental;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "equipments")
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private EquipmentType type;

    @Column(nullable = false)
    private Integer totalAmount = 0;

    @Column(nullable = false)
    private Integer activeAmount = 0;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Rental rental;
}
