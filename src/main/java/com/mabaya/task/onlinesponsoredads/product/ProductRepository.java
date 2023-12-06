package com.mabaya.task.onlinesponsoredads.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE Product p " +
            "SET p.category = :newCategory" +
            " WHERE p.id = :productId")
    void updateCategory(
            @Param("productId") Long productId,
            @Param("newCategory") String newCategory);

    @Transactional
    @Modifying
    @Query("UPDATE Product p " +
            "SET p.price = :newPrice" +
            " WHERE p.id = :productId")
    void updatePrice(
            @Param("productId") Long productId,
            @Param("newPrice") double newPrice);

    @Query("SELECT p FROM Product p")
    List<Product> getAll();

    @Query("SELECT p FROM Product " +
            "p WHERE p.serialNumber = :serialNumber")
    Optional<Product> findBySerialNumber(@Param("serialNumber") int serialNumber);
}