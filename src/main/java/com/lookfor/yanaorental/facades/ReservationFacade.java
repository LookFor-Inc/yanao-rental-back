package com.lookfor.yanaorental.facades;

import com.lookfor.json.schemas.generated.reservation.ReservationEquipmentTypesAndRentalsV1;

import java.util.function.Supplier;

public interface ReservationFacade {
    <T extends ReservationEquipmentTypesAndRentalsV1> T fetchEquipmentTypesAndRentals(Supplier<T> responseCreator);
}
