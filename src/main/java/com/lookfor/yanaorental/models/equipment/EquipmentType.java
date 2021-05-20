package com.lookfor.yanaorental.models.equipment;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.net.URL;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "equipment_types")
public class EquipmentType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 128, unique = true)
    private String name;

    @Column(length = 128, nullable = false)
    private URL img;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private EquipmentCategory category;

    @OneToMany(mappedBy = "type")
    private List<Equipment> equipments;
}
