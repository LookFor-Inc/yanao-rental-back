package com.lookfor.yanaorental.services;

import com.lookfor.yanaorental.models.Rental;

import java.util.List;
import java.util.function.Function;

public interface RentalService {
    <T> T fetchAllByEquipmentTypeIds(List<Long> equipmentTypeIds, Function<List<Rental>, T> toDto);

    <T> T fetchAll(Function<List<Rental>, T> toDto);
}
