package com.lookfor.yanaorental.services;

import com.lookfor.yanaorental.models.equipment.EquipmentCategory;

import java.util.List;

public interface EquipmentCategoryService {
    List<EquipmentCategory> fetchAll();
}
