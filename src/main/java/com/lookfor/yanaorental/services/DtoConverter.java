package com.lookfor.yanaorental.services;

import com.lookfor.json.schemas.generated.equipment_category.EquipmentCategoryItemV1;
import com.lookfor.json.schemas.generated.equipment_category.EquipmentTypeItemV1;
import com.lookfor.json.schemas.generated.rental.RentalItemV1;
import com.lookfor.yanaorental.models.Rental;
import com.lookfor.yanaorental.models.equipment.EquipmentCategory;
import com.lookfor.yanaorental.models.equipment.EquipmentType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.net.URL;
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

    public List<EquipmentCategoryItemV1> toEquipmentCategoryItemList(List<EquipmentCategory> equipmentCategories) {
        return equipmentCategories
                .stream()
                .map(ec -> {
                    EquipmentCategoryItemV1 item = new EquipmentCategoryItemV1();
                    item.setId(ec.getId());
                    item.setName(ec.getName());
                    try {
                        item.setImg(new URL("http://localhost:8080"));
                    } catch (MalformedURLException exc) {
                        log.error(exc.getMessage());
                    }
                    item.setTypes(toEquipmentTypeItemList(ec.getTypes()));
                    return item;
                })
                .sorted(Comparator.comparing(EquipmentCategoryItemV1::getName))
                .collect(Collectors.toList());
    }

    public List<RentalItemV1> toRentalItemList(List<Rental> rentals) {
        return rentals
                .stream()
                .map(r -> {
                    RentalItemV1 item = new RentalItemV1();
                    item.setId(r.getId());
                    item.setName(r.getName());
                    item.setAddress(r.getAddress());
                    // TODO: add coordinates
                    return item;
                })
                .sorted(Comparator.comparing(RentalItemV1::getAddress))
                .collect(Collectors.toList());
    }
}
