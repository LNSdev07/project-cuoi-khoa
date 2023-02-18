package com.t3h.ecommerce.entities.product;

import com.t3h.ecommerce.entities.BaseEntity;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "image")
public class Image extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    @Column(name = "url")
    private String url;


    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
}
