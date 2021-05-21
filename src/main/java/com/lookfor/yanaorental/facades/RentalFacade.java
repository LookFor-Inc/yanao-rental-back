package com.lookfor.yanaorental.facades;

import com.lookfor.json.schemas.generated.rental.RentalItemResponse;

import java.util.function.Supplier;

public interface RentalFacade {
    <T extends RentalItemResponse> T getRentalItemResponse(
            long id,
            Supplier<T> responseCreator
    );
}
