package com.lookfor.yanaorental.services;

import com.lookfor.yanaorental.models.equipment.EquipmentType;

import java.util.List;

public interface EquipmentTypeService {
    List<EquipmentType> fetchAll();

    List<EquipmentType> fetchByRentalId(long rentalId);
}
