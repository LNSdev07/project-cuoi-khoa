package com.t3h.ecommerce.repositories;


import com.t3h.ecommerce.entities.product.Color;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ColorRepository extends CrudRepository<Color, Long> {


    @Query("select c from Color c where (:textSearch = '' or" +
            " lower(c.colorName) like concat('%', lower(:textSearch) ,'%'))")
    Page<Color> findColor(Pageable pageable,
                          @Param("textSearch") String textSearch);
}
