package com.lookfor.yanaorental.services;

import com.lookfor.yanaorental.models.Rental;

import java.util.List;
import java.util.function.Function;

public interface RentalService {
    <T> T fetchAllByEquipmentType(long equipmentTypeId, Function<List<Rental>, T> toDto);
}
