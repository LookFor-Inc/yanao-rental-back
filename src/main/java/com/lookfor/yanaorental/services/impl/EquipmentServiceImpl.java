package com.lookfor.yanaorental.services.impl;

import com.lookfor.yanaorental.annotations.TransactionReadOnly;
import com.lookfor.yanaorental.models.equipment.EquipmentData;
import com.lookfor.yanaorental.repositories.EquipmentRepository;
import com.lookfor.yanaorental.services.EquipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EquipmentServiceImpl implements EquipmentService {
    private final EquipmentRepository equipmentRepository;

    @Override
    @TransactionReadOnly
    public List<EquipmentData> fetchEquipmentsDataByRentalId(long id) {
        return equipmentRepository.findEquipmentDataByRentalId(id);
    }
}
