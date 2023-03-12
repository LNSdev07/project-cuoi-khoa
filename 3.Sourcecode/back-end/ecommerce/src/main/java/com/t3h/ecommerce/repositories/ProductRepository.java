package com.t3h.ecommerce.repositories;

import com.t3h.ecommerce.dto.request.admin_product.ProductAdmin;
import com.t3h.ecommerce.entities.product.Category;
import com.t3h.ecommerce.entities.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;


@Repository
@Transactional
public interface ProductRepository extends JpaRepository<Product, Long> {

   @Query("SELECT\n" +
           "    P.id as id, P.productName as productName, P.shortDescription as shortDescription," +
           "P.cost as cost, P.quantity as quantity, P.createdDate as createdDate, P.updatedDate as updatedDate," +
           " c.id as categoryId\n" +
           "FROM Product AS P JOIN P.category c\n" +
           "WHERE (c.id =:categoryId or :categoryId=0) " +
           "and (P.productName LIKE concat('%', :productName, '%')) and\n" +
           "((P.quantity between :quantity and :quantity+10) or :quantity=0) and\n" +
           "((P.cost between :cost and :cost+100) or :cost=0) and\n" +
           "((P.createdDate between :createdDateStart and :createdDateEnd) or :createdDateStart=0) and\n" +
           "((P.updatedDate between :updatedDateStart and :updatedDateEnd) or :updatedDateStart=0) " )
   Page<ProductAdmin> findProduct(Pageable pageable,
                                  @Param("productName") String productName,
                                  @Param("quantity") Long quantity,
                                  @Param("cost") Double cost,
                                  @Param("categoryId") Long categoryId,
                                  @Param("createdDateStart") Long createdDateStart,
                                  @Param("createdDateEnd") Long createdDateEnd,
                                  @Param("updatedDateStart") Long updatedDateStart,
                                  @Param("updatedDateEnd") Long updatedDateEnd);


   @Modifying
   @Query("delete from Product p where p.id in :ids")
   void deleteProduct(@Param("ids") List<Long> ids);

   @Query(value = "select p from Product  p")
   Page<Product> findProductForHome(Pageable pageable);

   @Query(value = "select p from Product  p")
   Page<Product> findProductForShop(Pageable pageable);


}
