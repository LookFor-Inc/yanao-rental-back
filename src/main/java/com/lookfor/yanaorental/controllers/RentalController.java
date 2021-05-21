package com.lookfor.yanaorental.controllers;

import com.lookfor.json.schemas.generated.rental.RentalPublishRequest;
import com.lookfor.json.schemas.generated.rental.RentalPublishResponse;
import com.lookfor.yanaorental.services.RentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/api/v1/rental")
@RequiredArgsConstructor
public class RentalController {
    private final RentalService rentalService;

    @PostMapping
    public RentalPublishResponse publish(@RequestBody @Valid RentalPublishRequest request) {
        // TODO: add user
        return rentalService.save(request, RentalPublishResponse::new);
    }

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
