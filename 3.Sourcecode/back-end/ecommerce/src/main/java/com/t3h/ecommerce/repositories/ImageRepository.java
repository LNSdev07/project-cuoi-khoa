package com.t3h.ecommerce.repositories;


import com.t3h.ecommerce.entities.product.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface ImageRepository extends JpaRepository<Image, Long> {

    @Query(value = "select * from Image I where I.product_id =:id", nativeQuery = true)
    List<Image> findByProductId(@Param("id") Long id);
}
