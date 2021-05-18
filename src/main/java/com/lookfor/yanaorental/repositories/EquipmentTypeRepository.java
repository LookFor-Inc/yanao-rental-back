package com.lookfor.yanaorental.repositories;

import com.lookfor.yanaorental.models.equipment.EquipmentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipmentTypeRepository extends JpaRepository<EquipmentType, Long> {
}
