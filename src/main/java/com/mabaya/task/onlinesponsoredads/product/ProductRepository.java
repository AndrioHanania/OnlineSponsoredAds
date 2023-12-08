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

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value =
            "DELETE FROM campaign_products cp " +
                    "WHERE cp.product_id = :productId")
    void removeProductFrom_campaign_products(@Param("productId") Long productId);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value =
            "DROP TABLE campaign_products")
    void drop_campaign_products();

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value =
            "DROP TABLE products")
    void drop_products();

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value =
            "DROP SEQUENCE product_sequence")
    void drop_product_sequence();

    @Query("SELECT COUNT(p) FROM Product p")
    long count();

    @Query(nativeQuery = true, value =
            "SELECT *" +
                    "FROM (" +
                    "         (SELECT p.category_id, ptc.product_id as id, p.serial_number, p.title, p.price" +
                    "          FROM campaigns cm, products p, categories ct, campaign_products ptc" +
                    "          WHERE cm.id = ptc.campaign_id" +
                    "            AND cm.active = true" +
                    "            AND ptc.product_id = p.id" +
                    "            AND ct.id = p.category_id" +
                    "            AND ct.name = :categoryName" +
                    "          ORDER BY cm.bid DESC" +
                    "          LIMIT 1)" +
                    "         UNION ALL" +
                    "         (SELECT p.category_id, ptc.product_id as id, p.serial_number, p.title, p.price" +
                    "          FROM campaigns cm, products p, categories ct, campaign_products ptc" +
                    "          WHERE cm.id = ptc.campaign_id" +
                    "            AND cm.active = true" +
                    "            AND ptc.product_id = p.id" +
                    "            AND ct.id = p.category_id" +
                    "            AND ct.name != :categoryName" +
                    "          ORDER BY cm.bid DESC" +
                    "          LIMIT 1)" +
                    "     ) AS bests " +
                    "LIMIT 1")
    Optional<Product> getProductWithMaxBidByCategoryName(@Param("categoryName") String categoryName);
    /**
     It is better for me to ask the DB once than to ask twice with lower time efficiency.
     Unless this query is run a lot (although, I can use previous answers).
     It also depends on the chances that the category will be empty or not.
     There are many ways to make the query.
     */
}