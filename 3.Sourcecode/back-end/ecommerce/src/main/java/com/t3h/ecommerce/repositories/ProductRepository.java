package com.t3h.ecommerce.repositories;

import com.t3h.ecommerce.entities.product.Product;
import com.t3h.ecommerce.pojo.dto.product.ProductDB;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@Transactional
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "SELECT\n" +
            "   P.id as id,\n" +
            "   P.product_name as productName,\n" +
            "   P.cost as cost,\n" +
            "   P.img_avt as url\n" +
            "FROM product AS P\n" +
            "INNER  JOIN image AS I ON P.id = I.product_id\n" +
            "INNER JOIN color c on P.id = c.product_id\n" +
            "INNER  JOIN size s on P.id = s.product_id\n" +
            "where (:testEmptyColorCode =1 OR c.color_code in :listColorCode)\n" +
            "AND (:testEmptySize =1 OR s.size_code in :listSizeCode)", countQuery = "SELECT\n" +
            "   count(P.id)\n" +
            "FROM product AS P\n" +
            "INNER  JOIN image AS I ON P.id = I.product_id\n" +
            "INNER JOIN color c on P.id = c.product_id\n" +
            "INNER  JOIN size s on P.id = s.product_id\n" +
            "where (:testEmptyColorCode =1 OR c.color_code in :listColorCode)\n" +
            "AND (:testEmptySize =1 OR s.size_code in :listSizeCode)", nativeQuery = true)
    Page<ProductDB> findProductWithFilter( Pageable pageable,
                               @Param("testEmptyColorCode") Integer testEmptyColorCode,
                               @Param("listColorCode") List<String> listColorCode,
                               @Param("testEmptySize") Integer testEmptySize,
                               @Param("listSizeCode") List<String> listSizeCode);


    @Query("DELETE FROM Product P WHERE P.category = :Id")
    void deleteProductByCategoryId(@Param("Id") Long Id);



    @Query("SELECT P FROM Product P WHERE ( (P.productName LIKE '' OR LOWER(P.productName) LIKE CONCAT('%', LOWER(:textSearch), '%')) AND " +
            "(P.cost between :minCost and :maxCost) AND (P.quantity between :minQuantity and :maxQuantity))")
    Page<Product> findProduct(Pageable pageable,
                              @Param("textSearch") String textSearch,
                              @Param("minCost") Double minCost,
                              @Param("maxCost") Double maxCost,
                              @Param("minQuantity") Long minQuantity,
                              @Param("maxQuantity") Long maxQuantity);
}
