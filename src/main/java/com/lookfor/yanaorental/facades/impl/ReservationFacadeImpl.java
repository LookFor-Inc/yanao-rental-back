package com.lookfor.yanaorental.facades.impl;

import com.lookfor.json.schemas.generated.equipment_category.EquipmentCategoryItemV1;
import com.lookfor.json.schemas.generated.equipment_category.EquipmentTypeItemV1;
import com.lookfor.json.schemas.generated.reservation.ReservationEquipmentTypesAndRentalsV1;
import com.lookfor.yanaorental.facades.ReservationFacade;
import com.lookfor.yanaorental.models.Rental;
import com.lookfor.yanaorental.models.equipment.EquipmentCategory;
import com.lookfor.yanaorental.models.equipment.EquipmentType;
import com.lookfor.yanaorental.services.DtoConverter;
import com.lookfor.yanaorental.services.EquipmentCategoryService;
import com.lookfor.yanaorental.services.EquipmentTypeService;
import com.lookfor.yanaorental.services.RentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ReservationFacadeImpl implements ReservationFacade {
    private final RentalService rentalService;
    private final EquipmentTypeService equipmentTypeService;
    private final EquipmentCategoryService equipmentCategoryService;

    private final DtoConverter dtoConverter;

    @Override
    @Transactional(readOnly = true)
    public <T extends ReservationEquipmentTypesAndRentalsV1> T fetchEquipmentTypesAndRentals(
            Supplier<T> responseCreator
    ) {
        T response = responseCreator.get();

        List<EquipmentCategory> equipmentCategories = equipmentCategoryService.fetchAll();
        List<Rental> rentals = rentalService.fetchAll();

        response.setEquipmentCategories(dtoConverter.toEquipmentCategoryItemList(equipmentCategories));
        response.setRentals(dtoConverter.toRentalItemList(rentals));
        return response;
    }

    @Override
    @Transactional(readOnly = true)
    public <T extends ReservationEquipmentTypesAndRentalsV1> T fetchEquipmentTypesAndRentalsByEquipmentTypes(
            List<Long> equipmentTypeIds,
            Supplier<T> responseCreator
    ) {
        T response = responseCreator.get();

        List<EquipmentCategory> equipmentCategories = equipmentCategoryService.fetchAll();
        List<Rental> rentals = rentalService.fetchByEquipmentTypeIds(equipmentTypeIds);

        response.setEquipmentCategories(dtoConverter.toEquipmentCategoryItemList(equipmentCategories));
        response.setRentals(dtoConverter.toRentalItemList(rentals));
        return response;
    }

    @Override
    @Transactional(readOnly = true)
    public <T extends ReservationEquipmentTypesAndRentalsV1> T fetchEquipmentTypesAndRentalByRental(
            long rentalId,
            Supplier<T> responseCreator
    ) {
        T response = responseCreator.get();

        Map<EquipmentCategory, List<EquipmentType>> byEquipmentCategory =
                equipmentTypeService.fetchByRentalId(rentalId)
                        .stream()
                        .collect(Collectors.groupingBy(
                                EquipmentType::getCategory));

        List<EquipmentCategoryItemV1> equipmentCategoryItemList =
                equipmentCategoryService.fetchAll()
                        .stream()
                        .map(ec -> {
                            EquipmentCategoryItemV1 ecItem = new EquipmentCategoryItemV1();
                            ecItem.setId(ec.getId());
                            ecItem.setName(ec.getName());
                            ecItem.setImg(ec.getImg());

                            List<EquipmentTypeItemV1> equipmentTypeItemList =
                                    ec.getTypes()
                                            .stream()
                                            .map(et -> {
                                                EquipmentTypeItemV1 etItem = new EquipmentTypeItemV1();
                                                etItem.setId(et.getId());
                                                etItem.setName(et.getName());
                                                etItem.setImg(et.getImg());
                                                if (!byEquipmentCategory.containsKey(ec)) {
                                                    ecItem.setAvailable(false);
                                                    etItem.setAvailable(false);
                                                } else if (!byEquipmentCategory.get(ec).contains(et)) {
                                                    etItem.setAvailable(false);
                                                }
                                                return etItem;
                                            })
                                            .collect(Collectors.toList());

                            ecItem.setTypes(equipmentTypeItemList);
                            return ecItem;
                        })
                        .collect(Collectors.toList());

        List<Rental> rentals = rentalService.fetchAll();

        response.setEquipmentCategories(equipmentCategoryItemList);
        response.setRentals(dtoConverter.toRentalItemList(rentals));
        return response;
    }
}
