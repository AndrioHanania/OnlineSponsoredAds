package com.mabaya.task.onlinesponsoredads.category;
import com.mabaya.task.onlinesponsoredads.campaign.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT c FROM Category c " +
            "WHERE LOWER(c.name) = LOWER(:name)")
    Optional<Category> findByName(@Param("name") String name);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value =
            "DROP TABLE categories")
    void dropCategories();

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value =
            "DROP SEQUENCE category_sequence")
    void drop_category_sequence();

    @Query("SELECT COUNT(c) FROM Category c")
    long count();
}
