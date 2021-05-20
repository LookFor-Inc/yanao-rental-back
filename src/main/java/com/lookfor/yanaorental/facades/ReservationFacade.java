package com.lookfor.yanaorental.facades;

import com.lookfor.json.schemas.generated.reservation.ReservationEquipmentTypesAndRentalsV1;

import java.util.List;
import java.util.function.Supplier;

public interface ReservationFacade {
    /**
     * Fetch all equipment types and rentals without filter
     *
     * @param responseCreator creator
     * @param <T>             object
     * @return response json
     */
    <T extends ReservationEquipmentTypesAndRentalsV1> T fetchEquipmentTypesAndRentals(
            Supplier<T> responseCreator
    );

    /**
     * Fetch all equipment types and rentals by equipment types' ids
     *
     * @param equipmentTypeIds list of ids of equipment types
     * @param responseCreator  creator
     * @param <T>              object
     * @return response json
     */
    <T extends ReservationEquipmentTypesAndRentalsV1> T fetchEquipmentTypesAndRentalsByEquipmentTypes(
            List<Long> equipmentTypeIds,
            Supplier<T> responseCreator
    );

    /**
     * Fetch equipment types by rental and all rentals
     *
     * @param rentalId        id of a rental
     * @param responseCreator creator
     * @param <T>             object
     * @return response json
     */
    <T extends ReservationEquipmentTypesAndRentalsV1> T fetchEquipmentTypesAndRentalByRental(
            long rentalId,
            Supplier<T> responseCreator
    );
}
