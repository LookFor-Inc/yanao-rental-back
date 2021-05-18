package com.lookfor.yanaorental.models.equipment;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "equipment_types")
public class EquipmentType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 128, unique = true)
    private String name;

    @ManyToOne(optional = false)
    private EquipmentCategory category;

    @OneToMany(mappedBy = "type")
    private List<Equipment> equipments;
}
