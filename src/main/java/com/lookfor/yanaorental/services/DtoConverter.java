package com.lookfor.yanaorental.services;

import com.lookfor.json.schemas.generated.equipment_category.EquipmentCategoryGetAllResponse;
import com.lookfor.json.schemas.generated.equipment_category.EquipmentCategoryItemV1;
import com.lookfor.json.schemas.generated.equipment_category.EquipmentTypeItemV1;
import com.lookfor.json.schemas.generated.rental.RentalGetAllByEquipmentTypeResponse;
import com.lookfor.json.schemas.generated.rental.RentalItemV1;
import com.lookfor.yanaorental.models.Rental;
import com.lookfor.yanaorental.models.equipment.EquipmentCategory;
import com.lookfor.yanaorental.models.equipment.EquipmentType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class DtoConverter {
    public List<EquipmentTypeItemV1> toEquipmentTypeItemList(List<EquipmentType> equipmentTypes) {
        return equipmentTypes
                .stream()
                .map(et -> {
                    EquipmentTypeItemV1 item = new EquipmentTypeItemV1();
                    item.setId(et.getId());
                    item.setName(et.getName());
                    return item;
                })
                .collect(Collectors.toList());
    }

    public EquipmentCategoryGetAllResponse toEquipmentCategoryGetAllResponse(List<EquipmentCategory> equipmentCategories) {
        EquipmentCategoryGetAllResponse response = new EquipmentCategoryGetAllResponse();
        List<EquipmentCategoryItemV1> categories = equipmentCategories
                .stream()
                .map(ec -> {
                    EquipmentCategoryItemV1 item = new EquipmentCategoryItemV1();
                    item.setName(ec.getName());
                    item.setTypes(toEquipmentTypeItemList(ec.getTypes()));
                    return item;
                })
                .sorted(Comparator.comparing(
                        EquipmentCategoryItemV1::getName))
                .collect(Collectors.toList());
        response.setCategories(categories);
        return response;
    }

    public RentalGetAllByEquipmentTypeResponse toRentalGetAllByEquipmentTypeResponse(List<Rental> rentals) {
        RentalGetAllByEquipmentTypeResponse response = new RentalGetAllByEquipmentTypeResponse();
        List<RentalItemV1> rentalList = rentals
                .stream()
                .map(r -> {
                    RentalItemV1 item = new RentalItemV1();
                    item.setId(r.getId());
                    item.setName(r.getName());
                    item.setTown(r.getTown());
                    return item;
                })
                .sorted(Comparator.comparing(
                        RentalItemV1::getTown))
                .collect(Collectors.toList());
        response.setRentals(rentalList);
        return response;
    }
}
