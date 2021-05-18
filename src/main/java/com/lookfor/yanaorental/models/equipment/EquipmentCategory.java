package com.lookfor.yanaorental.models.equipment;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "equipment_categories")
public class EquipmentCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 128, unique = true)
    private String name;

    @OneToMany(mappedBy = "category")
    private List<EquipmentType> types;
}
