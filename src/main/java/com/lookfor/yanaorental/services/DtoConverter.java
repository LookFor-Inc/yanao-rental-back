package com.lookfor.yanaorental.services;

import com.lookfor.json.schemas.generated.equipment_category.EquipmentCategoryItemV1;
import com.lookfor.json.schemas.generated.equipment_category.EquipmentTypeItemV1;
import com.lookfor.json.schemas.generated.rental.RentalAllListResponse;
import com.lookfor.json.schemas.generated.rental.RentalAllListResponseV1;
import com.lookfor.json.schemas.generated.rental.RentalItemV1;
import com.lookfor.json.schemas.generated.rental.WorkTimeItemV1;
import com.lookfor.yanaorental.models.rental.Rental;
import com.lookfor.yanaorental.models.equipment.EquipmentCategory;
import com.lookfor.yanaorental.models.equipment.EquipmentType;
import com.lookfor.yanaorental.models.rental.WorkTime;
import com.lookfor.yanaorental.utils.DateUtils;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
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

    public RentalAllListResponse toRentalAllListResponse(List<Rental> rentals) {
        RentalAllListResponse item = new RentalAllListResponse();
        item.setRentalsTotal(rentals.size());
        item.setRentals(toRentalItemList(rentals));
        return item;
    }

    public List<RentalItemV1> toRentalItemList(List<Rental> rentals) {
        return rentals
                .stream()
                .map(r -> {
                    RentalItemV1 item = new RentalItemV1();
                    item.setId(r.getId());
                    item.setName(r.getName());
                    item.setImg(r.getImg());
                    item.setAddress(r.getAddress());
                    item.setLatitude(r.getLatitude());
                    item.setLongitude(r.getLongitude());
                    item.setWorkTime(toWorkTimeItemList(r.getWorkTimes()));
                    return item;
                })
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
}
