package com.lookfor.yanaorental.services;

import com.lookfor.json.schemas.generated.equipment.EquipmentItemV1;
import com.lookfor.json.schemas.generated.equipment_category.EquipmentCategoryItemV1;
import com.lookfor.json.schemas.generated.equipment_category.EquipmentTypeItemV1;
import com.lookfor.json.schemas.generated.rental.*;
import com.lookfor.yanaorental.models.equipment.EquipmentData;
import com.lookfor.yanaorental.models.rental.Rental;
import com.lookfor.yanaorental.models.equipment.EquipmentCategory;
import com.lookfor.yanaorental.models.equipment.EquipmentType;
import com.lookfor.yanaorental.models.rental.WorkTime;
import com.lookfor.yanaorental.utils.DateUtils;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class DtoConverter {
    public List<EquipmentTypeItemV1> toEquipmentTypeItemList(List<EquipmentType> equipmentTypes) {
        return equipmentTypes
                .stream()
                .map(et -> {
                    EquipmentTypeItemV1 item = new EquipmentTypeItemV1();
                    item.setId(et.getId());
                    item.setName(et.getName());
                    item.setImg(et.getImg());
                    return item;
                })
                .collect(Collectors.toList());
    }

    public EquipmentItemV1 toEquipmentItemFromData(EquipmentData equipmentData) {
        EquipmentItemV1 item = new EquipmentItemV1();
        item.setId(equipmentData.getId());
        item.setName(equipmentData.getName());
        item.setImg(equipmentData.getImg());
        item.setPrice(equipmentData.getPrice().longValue());
        item.setTotalCount(equipmentData.getTotalCount());
        item.setFreeCount(equipmentData.getFreeCount());
        return item;
    }

    public List<EquipmentCategoryItemV1> toEquipmentCategoryItemList(List<EquipmentCategory> equipmentCategories) {
        return equipmentCategories
                .stream()
                .map(ec -> {
                    EquipmentCategoryItemV1 item = new EquipmentCategoryItemV1();
                    item.setId(ec.getId());
                    item.setName(ec.getName());
                    item.setImg(ec.getImg());
                    item.setTypes(toEquipmentTypeItemList(ec.getTypes()));
                    return item;
                })
                .sorted(Comparator.comparing(EquipmentCategoryItemV1::getName))
                .collect(Collectors.toList());
    }

    public RentalItemV1 toRentalItem(Rental rental) {
        RentalItemV1 item = new RentalItemV1();
        item.setId(rental.getId());
        item.setName(rental.getName());
        item.setImg(rental.getImg());
        item.setAddress(rental.getAddress());
        item.setLatitude(rental.getLatitude());
        item.setLongitude(rental.getLongitude());
        item.setWorkTime(toWorkTimeItemList(rental.getWorkTimes()));
        return item;
    }

    public List<RentalItemV1> toRentalItemList(List<Rental> rentals) {
        return rentals
                .stream()
                .map(this::toRentalItem)
                .sorted(Comparator.comparing(RentalItemV1::getAddress))
                .collect(Collectors.toList());
    }

    public List<WorkTimeItemV1> toWorkTimeItemList(List<WorkTime> workTimes) {
        return workTimes
                .stream()
                .map(w -> {
                    WorkTimeItemV1 item = new WorkTimeItemV1();
                    item.setFromDay(w.getFromDay().name());
                    item.setFromTime(DateUtils.getTimeStringFromDate(w.getFromTime()));
                    item.setToDay(w.getToDay().name());
                    item.setToTime(DateUtils.getTimeStringFromDate(w.getToTime()));
                    return item;
                })
                .collect(Collectors.toList());
    }

    public RentalAllListResponse toRentalAllListResponse(List<Rental> rentals) {
        RentalAllListResponse item = new RentalAllListResponse();
        item.setRentalsTotal(rentals.size());
        item.setRentals(toRentalItemList(rentals));
        return item;
    }

//    public RentalItemResponse toRentalItemResponse(Rental rental) {
//        RentalItemResponse item = new RentalItemResponse();
//        item.setInfo(toRentalItem(rental));
//        item.setCategories(toEquipmentCategoryItemList(rental.getEquipments()));
//
//        Map<String, EquipmentCategoryItemV1> categoryMap = new HashMap<>();
//
//        rental.getEquipments()
//                .stream()
//                .forEach(e -> {
//
//                    categoryMap.get(e.getType().getCategory().getName())
//                });
//        EquipmentCategoryItemV1 category
//        return item;
//    }
}
