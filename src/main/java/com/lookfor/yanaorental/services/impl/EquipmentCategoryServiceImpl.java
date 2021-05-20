package com.lookfor.yanaorental.services.impl;

import com.lookfor.yanaorental.models.equipment.EquipmentCategory;
import com.lookfor.yanaorental.repositories.EquipmentCategoryRepository;
import com.lookfor.yanaorental.services.EquipmentCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EquipmentCategoryServiceImpl implements EquipmentCategoryService {
    private final EquipmentCategoryRepository equipmentCategoryRepository;

    @Override
    @Transactional(readOnly = true)
    public List<EquipmentCategory> fetchAll() {
        return equipmentCategoryRepository.findAll();
    }
}
