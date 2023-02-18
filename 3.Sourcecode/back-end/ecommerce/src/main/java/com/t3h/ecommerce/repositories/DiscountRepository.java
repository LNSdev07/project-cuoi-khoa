package com.t3h.ecommerce.repositories;

import com.t3h.ecommerce.entities.product.Discount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface DiscountRepository extends CrudRepository<Discount, Long> {


    @Query("select d from Discount d where ((:textSearch = '' or lower(d.discountName) like concat('%', lower(:textSearch),'%' )) " +
            "and (d.discountPercent between :minDis and :maxDis))")
    Page<Discount> findDiscount(Pageable pageable,
                                @Param("textSearch") String textSearch,
                                @Param("minDis") Float minDis,
                                @Param("maxDis") Float maxDis);
}
