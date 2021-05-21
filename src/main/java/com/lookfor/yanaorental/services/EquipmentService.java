package com.lookfor.yanaorental.services;

import com.lookfor.yanaorental.models.equipment.EquipmentData;

import java.util.List;

public interface EquipmentService {
    List<EquipmentData> fetchEquipmentsDataByRentalId(long id);
}
