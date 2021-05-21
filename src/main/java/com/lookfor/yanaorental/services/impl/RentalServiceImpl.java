package com.lookfor.yanaorental.services.impl;

import com.lookfor.json.schemas.generated.rental.RentalItemV1;
import com.lookfor.json.schemas.generated.rental.RentalPublishRequest;
import com.lookfor.yanaorental.annotations.TransactionReadOnly;
import com.lookfor.yanaorental.annotations.TransactionRequired;
import com.lookfor.yanaorental.exceptions.rest.NotFoundException;
import com.lookfor.yanaorental.exceptions.rest.UserIsNotInRentalException;
import com.lookfor.yanaorental.models.Rental;
import com.lookfor.yanaorental.models.user.User;
import com.lookfor.yanaorental.repositories.RentalRepository;
import com.lookfor.yanaorental.repositories.UserRepository;
import com.lookfor.yanaorental.services.RentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class RentalServiceImpl implements RentalService {
    private final RentalRepository rentalRepository;

    private final UserRepository userRepository;

    @Override
    @TransactionReadOnly
    public List<Rental> fetchAll() {
        return rentalRepository.findAll();
    }

    @Override
    @TransactionReadOnly
    public List<Rental> fetchByEquipmentTypeIds(List<Long> equipmentTypeIds) {
        return rentalRepository.findByEquipmentTypeIds(equipmentTypeIds);
    }

    @Override
    @TransactionRequired
    public <T extends RentalItemV1> T save(
            RentalPublishRequest request,
            long userId,
            Supplier<T> responseCreator
    ) {
        T response = responseCreator.get();

        User user = userRepository.findById(userId).orElseThrow(
                () -> new NotFoundException("User not found with id: " + userId));

        Rental rental = new Rental();
        rental.setName(request.getName());
        rental.setAddress(request.getAddress());
        rental.setOwner(user);
        rentalRepository.save(rental);

        response.setId(rental.getId());
        response.setName(rental.getName());
        response.setAddress(rental.getAddress());
        return response;
    }

    @Override
    @TransactionRequired
    public <T extends RentalItemV1> T saveImage(
            MultipartFile img,
            long rentalId,
            Supplier<T> responseCreator
    ) throws IOException {
        T response = responseCreator.get();
        File imgFile = new File(
                "src/main/resources/static/images/upload/" + img.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(imgFile);

        fos.write(img.getBytes());
        fos.close();

        Rental rental = fetchById(rentalId);
        rental.setImg(new URL(String.format("http://localhost:8080/images/upload/%s", imgFile.getName())));

        response.setId(rental.getId());
        response.setName(rental.getName());
        response.setAddress(rental.getAddress());
        response.setImg(rental.getImg());
        return response;
    }

    @Override
    @TransactionReadOnly
    public Rental fetchById(long id) {
        return rentalRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Rental with id " + id + " was not found"));
    }

    @Override
    @TransactionReadOnly
    public boolean isUserInRental(long rentalId, long userId) {
        if (!rentalRepository.existsByIdAndOwnerId(rentalId, userId)) {
            throw new UserIsNotInRentalException(
                    "User with id " + userId + " is not in a rental with id " + rentalId);
        }
        return true;
    }
}
