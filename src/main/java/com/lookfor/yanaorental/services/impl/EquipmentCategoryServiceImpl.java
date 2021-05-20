package com.lookfor.yanaorental.services.impl;

import com.lookfor.yanaorental.models.equipment.EquipmentCategory;
import com.lookfor.yanaorental.repositories.EquipmentCategoryRepository;
import com.lookfor.yanaorental.services.EquipmentCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class EquipmentCategoryServiceImpl implements EquipmentCategoryService {
    private final EquipmentCategoryRepository equipmentCategoryRepository;

    @Override
    @Transactional(readOnly = true)
    public <T> T fetchAll(Function<List<EquipmentCategory>, T> toDto) {
        List<EquipmentCategory> equipmentCategories = equipmentCategoryRepository.findAll();
        return toDto.apply(equipmentCategories);
    }
}
