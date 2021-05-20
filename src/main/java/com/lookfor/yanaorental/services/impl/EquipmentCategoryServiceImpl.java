package com.lookfor.yanaorental.services.impl;

import com.lookfor.yanaorental.annotations.TransactionReadOnly;
import com.lookfor.yanaorental.models.equipment.EquipmentCategory;
import com.lookfor.yanaorental.repositories.EquipmentCategoryRepository;
import com.lookfor.yanaorental.services.EquipmentCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EquipmentCategoryServiceImpl implements EquipmentCategoryService {
    private final EquipmentCategoryRepository equipmentCategoryRepository;

    @Override
    @TransactionReadOnly
    public List<EquipmentCategory> fetchAll() {
        return equipmentCategoryRepository.findAll();
    }
}
