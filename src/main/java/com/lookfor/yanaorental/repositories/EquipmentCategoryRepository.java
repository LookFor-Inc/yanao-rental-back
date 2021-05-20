package com.lookfor.yanaorental.repositories;

import com.lookfor.yanaorental.models.equipment.EquipmentCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipmentCategoryRepository extends JpaRepository<EquipmentCategory, Long> {
}
