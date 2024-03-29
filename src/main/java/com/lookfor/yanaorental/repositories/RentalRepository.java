package com.lookfor.yanaorental.repositories;

import com.lookfor.yanaorental.models.rental.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {
    @Query(
            value = """
                    SELECT DISTINCT * FROM rentals r1
                    WHERE r1.id IN
                    (SELECT r2.id FROM rentals r2
                    INNER JOIN equipments e ON r2.id = e.rental_id
                    WHERE e.type_id IN :ids
                    GROUP BY r2.id
                    HAVING COUNT(DISTINCT e.type_id) = :srcSize)
                    """,
            nativeQuery = true)
    List<Rental> findByEquipmentTypeIdsWithLength(List<Long> ids, int srcSize);

    default List<Rental> findByEquipmentTypeIds(List<Long> ids) {
        return findByEquipmentTypeIdsWithLength(ids, ids.size());
    }

    @Query("""
            SELECT (COUNT(r) > 0)
            FROM Rental r
            INNER JOIN r.owner u
            WHERE r.id = ?1 AND u.id = ?2
            """)
    boolean existsByIdAndOwnerId(long rentalId, long userId);
}
