package com.lookfor.yanaorental.services.impl;

import com.lookfor.yanaorental.exceptions.NotFoundException;
import com.lookfor.yanaorental.models.Rental;
import com.lookfor.yanaorental.repositories.RentalRepository;
import com.lookfor.yanaorental.services.RentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class RentalServiceImpl implements RentalService {
    private final RentalRepository rentalRepository;

    @Override
    @Transactional(readOnly = true)
    public <T> T fetchAllByEquipmentType(long equipmentTypeId, Function<List<Rental>, T> toDto) {
        List<Rental> rentals = rentalRepository.findAllByEquipmentTypeId(equipmentTypeId);
        if (rentals.isEmpty()) {
            throw new NotFoundException("Equipment type with id " + equipmentTypeId + " was not found");
        }
        return toDto.apply(rentals);
    }
}
