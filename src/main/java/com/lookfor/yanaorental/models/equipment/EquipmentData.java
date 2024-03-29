package com.lookfor.yanaorental.models.equipment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.net.URL;

@Getter
@Setter
@AllArgsConstructor
public class EquipmentData {
    private Long id;

    private String name;

    private URL img;

    private String category;

    private String type;

    private BigDecimal price;

    private Long totalCount;

    private Long freeCount;
}
