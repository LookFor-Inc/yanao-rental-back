package com.lookfor.yanaorental.repositories;

import com.lookfor.yanaorental.models.equipment.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
}
