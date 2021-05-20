package com.lookfor.yanaorental.repositories;

import com.lookfor.yanaorental.models.equipment.EquipmentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipmentTypeRepository extends JpaRepository<EquipmentType, Long> {
    @Query(value = """
            SELECT et FROM equipment_types et
            INNER JOIN equipments e ON e.type_id = et.id
            WHERE e.rental_id = :rentalId
            """,
            nativeQuery = true)
    List<EquipmentType> findByRentalId(long rentalId);
}
