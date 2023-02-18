package com.t3h.ecommerce.repositories;

import com.t3h.ecommerce.entities.order.OrderDetail;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface OrderDetailRepository extends PagingAndSortingRepository<OrderDetail, Long> {
}
