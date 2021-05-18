package com.lookfor.yanaorental.controllers;

import com.lookfor.json.schemas.generated.equipment_category.EquipmentCategoryGetAllResponse;
import com.lookfor.yanaorental.services.DtoConverter;
import com.lookfor.yanaorental.services.EquipmentCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/equipment-category")
@RequiredArgsConstructor
public class EquipmentCategoryController {
    private final EquipmentCategoryService equipmentCategoryService;

    private final DtoConverter dtoConverter;

    @GetMapping("/all")
    private EquipmentCategoryGetAllResponse takeAllCategories() {
        return equipmentCategoryService.fetchAll(dtoConverter::toEquipmentCategoryGetAllResponse);
    }
}
