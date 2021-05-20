package com.lookfor.yanaorental.services;

import com.lookfor.yanaorental.models.Rental;

import java.util.List;

public interface RentalService {
    List<Rental> fetchAll();
}
