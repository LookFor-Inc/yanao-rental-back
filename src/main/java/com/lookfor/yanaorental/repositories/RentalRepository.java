package com.lookfor.yanaorental.repositories;

import com.lookfor.yanaorental.models.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {
    @Query(
            value = """
                    SELECT * FROM rentals r 
                    INNER JOIN equipments e ON r.id=e.rental_id 
                    WHERE e.type_id=:equipmentTypeId
                    """,
            nativeQuery = true)
    List<Rental> findAllByEquipmentTypeId(long equipmentTypeId);
}
