package com.lookfor.yanaorental.services.impl;

import com.lookfor.yanaorental.annotations.TransactionReadOnly;
import com.lookfor.yanaorental.models.Rental;
import com.lookfor.yanaorental.repositories.RentalRepository;
import com.lookfor.yanaorental.services.RentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RentalServiceImpl implements RentalService {
    private final RentalRepository rentalRepository;

    @Override
    @TransactionReadOnly
    public List<Rental> fetchAll() {
        return rentalRepository.findAll();
    }

    @Override
    @TransactionReadOnly
    public List<Rental> fetchByEquipmentTypeIds(List<Long> equipmentTypeIds) {
        return rentalRepository.findByEquipmentTypeIds(equipmentTypeIds);
    }
}
