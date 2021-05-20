package com.lookfor.yanaorental.services;

import com.lookfor.yanaorental.models.equipment.EquipmentCategory;

import java.util.List;
import java.util.function.Function;

public interface EquipmentCategoryService {
    <T> T fetchAll(Function<List<EquipmentCategory>, T> toDto);
}
