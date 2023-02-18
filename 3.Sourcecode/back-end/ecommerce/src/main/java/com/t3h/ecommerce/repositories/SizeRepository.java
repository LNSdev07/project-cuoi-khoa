package com.t3h.ecommerce.repositories;

import com.t3h.ecommerce.entities.product.Size;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@Transactional
public interface SizeRepository extends JpaRepository<Size, Long> {

    @Query("select s from Size s where (lower(s.sizeName) like concat('%', lower(:textSearch),'%' ) or (:textSearch = '')) ")
    Page<Size> findSize(Pageable pageable,
                        @Param("textSearch") String textSearch);
}
