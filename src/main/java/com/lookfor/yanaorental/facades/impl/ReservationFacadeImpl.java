package com.lookfor.yanaorental.facades.impl;

import com.lookfor.json.schemas.generated.reservation.ReservationEquipmentTypesAndRentalsV1;
import com.lookfor.yanaorental.facades.ReservationFacade;
import com.lookfor.yanaorental.models.Rental;
import com.lookfor.yanaorental.models.equipment.EquipmentCategory;
import com.lookfor.yanaorental.services.DtoConverter;
import com.lookfor.yanaorental.services.EquipmentCategoryService;
import com.lookfor.yanaorental.services.RentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Supplier;

@Component
@RequiredArgsConstructor
public class ReservationFacadeImpl implements ReservationFacade {
    private final EquipmentCategoryService equipmentCategoryService;
    private final RentalService rentalService;

    private final DtoConverter dtoConverter;

    @Override
    @Transactional(readOnly = true)
    public <T extends ReservationEquipmentTypesAndRentalsV1> T fetchEquipmentTypesAndRentals(Supplier<T> responseCreator) {
        T response = responseCreator.get();
        List<EquipmentCategory> equipmentCategories = equipmentCategoryService.fetchAll();
        List<Rental> rentals = rentalService.fetchAll();
        response.setEquipmentCategories(dtoConverter.toEquipmentCategoryItemList(equipmentCategories));
        response.setRentals(dtoConverter.toRentalItemList(rentals));
        return response;
    }
}
