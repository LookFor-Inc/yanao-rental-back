package com.lookfor.yanaorental.controllers;

import com.lookfor.json.schemas.generated.rental.RentalGetAllByEquipmentTypeResponse;
import com.lookfor.yanaorental.exceptions.NotFoundException;
import com.lookfor.yanaorental.services.DtoConverter;
import com.lookfor.yanaorental.services.RentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/v1/rental")
@RequiredArgsConstructor
public class RentalController {
    private final RentalService rentalService;

    private final DtoConverter dtoConverter;

    @GetMapping("/all-by-type")
    public RentalGetAllByEquipmentTypeResponse takeAllRentalsByEquipmentType(@RequestParam long equipmentTypeId) {
        try {
            return rentalService.fetchAllByEquipmentType(equipmentTypeId, dtoConverter::toRentalGetAllByEquipmentTypeResponse);
        } catch (NotFoundException exc) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, exc.getMessage(), exc);
        }
    }
}
