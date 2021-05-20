package com.lookfor.yanaorental.services.impl;

import com.lookfor.yanaorental.annotations.TransactionReadOnly;
import com.lookfor.yanaorental.models.equipment.EquipmentType;
import com.lookfor.yanaorental.repositories.EquipmentTypeRepository;
import com.lookfor.yanaorental.services.EquipmentTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EquipmentTypeServiceImpl implements EquipmentTypeService {
    private final EquipmentTypeRepository equipmentTypeRepository;

    @Override
    @TransactionReadOnly
    public List<EquipmentType> fetchAll() {
        return equipmentTypeRepository.findAll();
    }

    @Override
    @TransactionReadOnly
    public List<EquipmentType> fetchByRentalId(long rentalId) {
        return equipmentTypeRepository.findByRentalId(rentalId);
    }
}
