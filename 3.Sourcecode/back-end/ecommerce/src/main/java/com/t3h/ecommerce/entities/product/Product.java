package com.t3h.ecommerce.entities.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.t3h.ecommerce.entities.BaseEntity;
import com.t3h.ecommerce.entities.order.OrderDetail;
import com.t3h.ecommerce.entities.order.Orders;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Table(name = "product")
@Entity
public class Product extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "short_description", length = 500)
    private String shortDescription;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @Column(name ="img_avt")
    private String imgAvt;

    @Column(name = "cost")
    private Double cost;

    @Column(name = "quantity")
    private Long quantity;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @JsonIgnore
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<Image> images;

    @JsonIgnore
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<Review> reviews;


    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "product_color",
    joinColumns = @JoinColumn(name = "product_id"),
    inverseJoinColumns = @JoinColumn(name = "color_id"))
    private List<Color> colors;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "product_size",
    joinColumns = @JoinColumn(name = "product_id"),
    inverseJoinColumns = @JoinColumn(name = "size_id"))
    private List<Size> sizes;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "product_discount",
    joinColumns = @JoinColumn(name = "product_id"),
    inverseJoinColumns = @JoinColumn(name = "discount_id"))
    private List<Discount> discounts;


    @JsonIgnore
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<OrderDetail> orderDetails;

}
