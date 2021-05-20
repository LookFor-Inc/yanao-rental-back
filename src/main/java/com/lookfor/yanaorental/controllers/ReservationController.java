package com.lookfor.yanaorental.controllers;

import com.lookfor.json.schemas.generated.reservation.ReservationGetEquipmentTypesAndRentalsResponse;
import com.lookfor.yanaorental.facades.ReservationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reservation")
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationFacade reservationFacade;

    @GetMapping("/equipment-types-and-rentals")
    public ReservationGetEquipmentTypesAndRentalsResponse takeEquipmentTypesAndRentals() {
        return reservationFacade.fetchEquipmentTypesAndRentals(
                ReservationGetEquipmentTypesAndRentalsResponse::new
        );
    }

    @GetMapping("/equipment-types-and-rentals/by-equipment-types")
    public ReservationGetEquipmentTypesAndRentalsResponse takeEquipmentTypesAndRentalsByEquipmentTypes(
            @RequestParam Long[] equipmentTypeIds
    ) {
        return reservationFacade.fetchEquipmentTypesAndRentalsByEquipmentTypes(
                List.of(equipmentTypeIds),
                ReservationGetEquipmentTypesAndRentalsResponse::new
        );
    }

    @GetMapping("/equipment-types-and-rentals/by-rental")
    public ReservationGetEquipmentTypesAndRentalsResponse takeEquipmentTypesAndRentalByEquipmentTypes(
            @RequestParam long rentalId
    ) {
        return reservationFacade.fetchEquipmentTypesAndRentalByRental(
                rentalId,
                ReservationGetEquipmentTypesAndRentalsResponse::new
        );
    }
}
