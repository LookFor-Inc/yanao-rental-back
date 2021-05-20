package com.lookfor.yanaorental.services.impl;

import com.lookfor.yanaorental.models.equipment.EquipmentType;
import com.lookfor.yanaorental.repositories.EquipmentTypeRepository;
import com.lookfor.yanaorental.services.EquipmentTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EquipmentTypeServiceImpl implements EquipmentTypeService {
    private final EquipmentTypeRepository equipmentTypeRepository;

    @Override
    @Transactional(readOnly = true)
    public List<EquipmentType> fetchAll() {
        return equipmentTypeRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<EquipmentType> fetchByRentalId(long rentalId) {
        return equipmentTypeRepository.findByRentalId(rentalId);
    }
}
