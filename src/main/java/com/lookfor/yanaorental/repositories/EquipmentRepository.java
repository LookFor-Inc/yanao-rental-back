package com.lookfor.yanaorental.repositories;

import com.lookfor.yanaorental.models.equipment.Equipment;
import com.lookfor.yanaorental.models.equipment.EquipmentData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
    @Query(value = """
           SELECT new com.lookfor.yanaorental.models.equipment.EquipmentData(e.id, e.name, e.img, ec.name, et.name, e.price, e.totalCount, e.freeCount)
           FROM Equipment e
           JOIN e.type et
           JOIN et.category ec
           WHERE e.rental.id = :id
           """)
    List<EquipmentData> findEquipmentDataByRentalId(long id);
}
