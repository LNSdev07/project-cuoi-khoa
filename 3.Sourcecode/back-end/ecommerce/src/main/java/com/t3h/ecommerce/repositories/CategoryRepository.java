package com.t3h.ecommerce.repositories;

import com.t3h.ecommerce.entities.product.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT C FROM Category C WHERE ((C.categoryName LIKE CONCAT('%', '', '%') AND :searchIsEmpty =1) " +
            "OR LOWER(C.categoryName) LIKE CONCAT('%', LOWER(:textSearch), '%'))")
    Page<Category> findCategory( Pageable pageable,
            @Param("searchIsEmpty") Integer searchIsEmpty,
            @Param("textSearch") String textSearch);
}
