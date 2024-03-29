package com.lookfor.yanaorental.controllers;

import com.lookfor.json.schemas.generated.rental.RentalAllListResponse;
import com.lookfor.json.schemas.generated.rental.RentalItemResponse;
import com.lookfor.json.schemas.generated.rental.RentalPublishRequest;
import com.lookfor.json.schemas.generated.rental.RentalPublishResponse;
import com.lookfor.yanaorental.annotations.AccessToRental;
import com.lookfor.yanaorental.annotations.CurrentUserId;
import com.lookfor.yanaorental.facades.RentalFacade;
import com.lookfor.yanaorental.services.DtoConverter;
import com.lookfor.yanaorental.services.EquipmentService;
import com.lookfor.yanaorental.services.RentalService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/api/v1/rental")
@SecurityRequirement(name = "bearerAuth")
@NoArgsConstructor
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RentalController {
    private RentalService rentalService;
    private DtoConverter dtoConverter;
    private RentalFacade rentalFacade;

    @PostMapping
    @PreAuthorize("hasRole('LANDLORD')")
    public RentalPublishResponse publish(
            @RequestBody @Valid RentalPublishRequest request,
            @CurrentUserId Long userId
    ) {
        return rentalService.save(request, userId, RentalPublishResponse::new);
    }

    @GetMapping
    public RentalAllListResponse takeAllRentals() {
        return rentalService.fetchAll(dtoConverter::toRentalAllListResponse);
    }

    @GetMapping("/{id}")
    public RentalItemResponse takeConcreteRental(@PathVariable Long id) {
        return rentalFacade.getRentalItemResponse(id, RentalItemResponse::new);
    }

    @AccessToRental(rentalId = "rentalId")
    @PostMapping(
            value = "/img",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public RentalPublishResponse publishGroupImage(
            @RequestPart final MultipartFile img,
            @RequestParam long rentalId
    ) throws IOException {
        return rentalService.saveImage(img, rentalId, RentalPublishResponse::new);
    }
}
