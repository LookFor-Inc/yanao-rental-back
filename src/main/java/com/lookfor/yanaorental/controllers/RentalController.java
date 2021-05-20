package com.lookfor.yanaorental.controllers;

import com.lookfor.json.schemas.generated.rental.RentalGetAllResponse;
import com.lookfor.yanaorental.exceptions.rest.NotFoundException;
import com.lookfor.yanaorental.services.DtoConverter;
import com.lookfor.yanaorental.services.RentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rental")
@RequiredArgsConstructor
public class RentalController {
    private final RentalService rentalService;

    private final DtoConverter dtoConverter;

    @GetMapping("/all-by-type")
    public RentalGetAllResponse takeAllRentalsByEquipmentType(@RequestParam Long[] equipmentTypeIds) {
        try {
            return rentalService.fetchAllByEquipmentTypeIds(
                    List.of(equipmentTypeIds),
                    dtoConverter::toRentalGetAllResponse);
        } catch (NotFoundException exc) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, exc.getMessage(), exc);
        }
    }

    @GetMapping("/all")
    public RentalGetAllResponse takeAllRentals() {
        return rentalService.fetchAll(dtoConverter::toRentalGetAllResponse);
    }
}