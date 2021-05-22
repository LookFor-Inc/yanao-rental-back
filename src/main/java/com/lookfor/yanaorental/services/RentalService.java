package com.lookfor.yanaorental.services;

import com.lookfor.json.schemas.generated.rental.RentalItemV1;
import com.lookfor.json.schemas.generated.rental.RentalPublishRequest;
import com.lookfor.yanaorental.models.rental.Rental;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public interface RentalService {
    Rental fetchById(long id);

    List<Rental> fetchAll();

    <T> T fetchAll(Function<List<Rental>, T> toDto);

    List<Rental> fetchByEquipmentTypeIds(List<Long> equipmentTypeIds);

    <T extends RentalItemV1> T save(RentalPublishRequest request, long userId, Supplier<T> responseCreator);

    <T extends RentalItemV1> T saveImage(MultipartFile img, long rentalId, Supplier<T> responseCreator) throws IOException;

    boolean isUserInRental(long rentalId, long userId);
}
