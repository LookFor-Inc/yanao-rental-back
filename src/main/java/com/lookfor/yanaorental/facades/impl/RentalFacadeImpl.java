package com.lookfor.yanaorental.facades.impl;

import com.lookfor.json.schemas.generated.equipment.EquipmentItemV1;
import com.lookfor.json.schemas.generated.rental.RentalItemResponse;
import com.lookfor.yanaorental.annotations.TransactionReadOnly;
import com.lookfor.yanaorental.facades.RentalFacade;
import com.lookfor.yanaorental.models.equipment.EquipmentData;
import com.lookfor.yanaorental.models.rental.Rental;
import com.lookfor.yanaorental.services.DtoConverter;
import com.lookfor.yanaorental.services.EquipmentService;
import com.lookfor.yanaorental.services.RentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

@Component
@RequiredArgsConstructor
public class RentalFacadeImpl implements RentalFacade {
    private final EquipmentService equipmentService;
    private final RentalService rentalService;
    private final DtoConverter dtoConverter;

    @Override
    @TransactionReadOnly
    public <T extends RentalItemResponse> T getRentalItemResponse(
            long id,
            Supplier<T> responseCreator
    ) {
        Rental rental = rentalService.fetchById(id);
        List<EquipmentData> equipmentData = equipmentService.fetchEquipmentsDataByRentalId(id);

        T response = responseCreator.get();
        Map<String, List<EquipmentItemV1>> equipmentMap = new HashMap<>();

        equipmentData
                .forEach(ed -> {
                    EquipmentItemV1 item = dtoConverter.toEquipmentItemFromData(ed);
                    equipmentMap.computeIfAbsent(ed.getCategory(), k -> new ArrayList<>());
                    equipmentMap.get(ed.getCategory()).add(item);
                });

        response.setInfo(dtoConverter.toRentalItem(rental));
        response.setAdditionalProperty("categories", equipmentMap);
        return response;
    }
}
