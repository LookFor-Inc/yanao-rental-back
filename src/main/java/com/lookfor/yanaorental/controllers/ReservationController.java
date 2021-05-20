package com.lookfor.yanaorental.controllers;

import com.lookfor.json.schemas.generated.reservation.ReservationGetEquipmentTypesAndRentalsResponse;
import com.lookfor.yanaorental.facades.ReservationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/reservation")
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationFacade reservationFacade;

    @GetMapping("/equipment-types-and-rentals")
    public ReservationGetEquipmentTypesAndRentalsResponse takeEquipmentTypesWithRentals() {
        return reservationFacade.fetchEquipmentTypesAndRentals(ReservationGetEquipmentTypesAndRentalsResponse::new);
    }
}
