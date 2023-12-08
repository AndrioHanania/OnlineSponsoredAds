package com.mabaya.task.onlinesponsoredads.campaign;

import com.mabaya.task.onlinesponsoredads.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
@Transactional(readOnly = true)
public interface CampaignRepository extends JpaRepository<Campaign, Long> {

    @Query("SELECT c FROM Campaign c")
    List<Campaign> getAll();

    @Query("SELECT c FROM Campaign c " +
            "WHERE LOWER(c.name) = LOWER(:name)")
    Optional<Campaign> findByName(
            @Param("name") String name);

    @Transactional
    @Modifying
    @Query("UPDATE Campaign c " +
            "SET c.bid = :newBid" +
            " WHERE c.id = :campaignId")
    void updateBid(
            @Param("campaignId") Long campaignId,
            @Param("newBid") double newBid);

    @Transactional
    @Modifying
    @Query("UPDATE Campaign c " +
            "SET c.products = :products" +
            " WHERE c.id = :campaignId")
    void updateProduct(
            @Param("campaignId") Long campaignId,
            @Param("products") Set<Product> products);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value =
            "DELETE FROM campaign_products cp " +
                    "WHERE cp.campaign_id = :campaignId")
    void removeCampaignFrom_campaign_products(
            @Param("campaignId") Long campaignId);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value =
            "DROP TABLE campaigns")
    void dropCampaigns();

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value =
            "DROP SEQUENCE campaign_sequence")
    void drop_campaign_sequence();

    @Query("SELECT COUNT(c) FROM Campaign c")
    long count();
}
