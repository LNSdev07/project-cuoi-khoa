package com.t3h.ecommerce.service.Impl;

import com.t3h.ecommerce.dto.request.admin_product.ProductAdminAddRequest;
import com.t3h.ecommerce.dto.request.admin_product.ProductAdminDTO;
import com.t3h.ecommerce.dto.request.admin_product.ProductAdminRequest;
import com.t3h.ecommerce.dto.request.home_product.ProductHomeDTO;
import com.t3h.ecommerce.dto.response.BaseResponse;
import com.t3h.ecommerce.entities.product.*;
import com.t3h.ecommerce.pojo.dto.product.ProductDTO;
import com.t3h.ecommerce.repositories.*;
import com.t3h.ecommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Autowired
    private final ProductRepository repository;

    @Autowired
    private final CategoryRepository categoryRepository;

    @Autowired
    private final ColorRepository colorRepository;

    @Autowired
    private final SizeRepository sizeRepository;

    @Autowired
    private final DiscountRepository discountRepository;

    @Autowired
    private final ImageRepository imageRepository;

    @Override
    public BaseResponse<?> findProduct(ProductAdminRequest request) {
        if(request == null) return  BaseResponse.builder().message("request not found!").status(HttpStatus.BAD_REQUEST.value()).build();

        try{
            Pageable pageable = PageRequest.of(request.getPageRequest().getPage()-1, request.getPageRequest().getPageSize(),
                    Sort.Direction.fromString(request.getPageRequest().getCondition().equals("asc")? "asc": "desc"),
                    (request.getPageRequest().getSortBy().isEmpty() || request.getPageRequest().getSortBy().equals("createdDate"))?
                    "createdDate": request.getPageRequest().getSortBy());

            Page<Product> page = repository.findProduct(pageable,
                    request.getProductName(), request.getQuantity(), request.getCost(), request.getCategoryId(),
                    request.getFilterDate().getCreatedDateStart(), request.getFilterDate().getCreatedDateEnd(),
                    request.getFilterDate().getUpdatedDateStart(), request.getFilterDate().getUpdatedDateEnd());

            if(page == null) return  BaseResponse.builder().message("request not found!").status(HttpStatus.BAD_REQUEST.value()).build();

            List<ProductAdminDTO>  list = page.getContent().stream().map(ProductAdminDTO::new).collect(Collectors.toList());

            return BaseResponse.builder().data(list).message("success").status(HttpStatus.OK.value()).totalRecords(page.getTotalElements()).build();

        }catch (Exception ex){
            log.error("can not call repository in product service");
            return BaseResponse.builder().message("request bad").status(HttpStatus.BAD_REQUEST.value()).build();
        }
    }

    @Override
    public BaseResponse<?> deleteProduct(String Ids) {
        if(Ids == null || Ids.isEmpty()) return BaseResponse.builder().message("delete fail").status(HttpStatus.BAD_REQUEST.value()).build();

        try{
            String[] arr = Ids.split(",");
            List<Long> ids = new ArrayList<>();
            for(int i=0 ;i< arr.length; i++){
                ids.add(Long.parseLong(arr[i]));
            }
            repository.deleteProduct(ids);
            return BaseResponse.builder().message("delete success").status(HttpStatus.OK.value()).build();
        } catch (Exception ex){
            return BaseResponse.builder().message("delete fail").status(HttpStatus.BAD_REQUEST.value()).build();
        }
    }


    @Override
    public BaseResponse<?> addProduct(ProductAdminAddRequest request) {
        try {
            boolean existColor =  colorRepository.existsById(request.getColorId());
            boolean existSize = sizeRepository.existsById(request.getSizeId());
            boolean existDiscount = sizeRepository.existsById(request.getDiscountId());
            boolean existCategory = categoryRepository.existsById(request.getCategoryId());

            if(!existColor || !existCategory || !existSize || !existDiscount){
                return BaseResponse.builder().message("request failed because not exist properties").status(HttpStatus.BAD_REQUEST.value()).build();
            }

            Color color = colorRepository.getReferenceById(request.getColorId());
            Size size = sizeRepository.getReferenceById(request.getSizeId());
            Discount discount = discountRepository.getReferenceById(request.getDiscountId());
            Category category = categoryRepository.getReferenceById(request.getCategoryId());

            Product product = new Product(new Date().getTime(), new Date().getTime(),
                    request.getProductName(), request.getShortDescription(), request.getDescription(),
                    request.getUrlImg().get(0), request.getCost(), request.getQuantity(),
                    category, size, discount, color);

            List<Image> images = request.getUrlImg().stream()
                    .map(url -> {
                        Image image = new Image(new Date().getTime(), new Date().getTime(), url);
                        image.setProduct(product);
                        return image;
                    })
                    .collect(Collectors.toList());

            product.setImages(images);

            repository.save(product);
            return BaseResponse.builder().message("create success").status(HttpStatus.OK.value()).build();

        }catch (Exception e){
            log.error("call repository failed!");
            return BaseResponse.builder().message("request failed").status(HttpStatus.BAD_REQUEST.value()).build();
        }
    }

    @Override
    public BaseResponse<?> findProductById(String id) {
        if(id == null || id.isEmpty()){
            return BaseResponse.builder().message("request failed").status(HttpStatus.BAD_REQUEST.value()).build();
        }
        try {
            Product product = repository.getReferenceById(Long.parseLong(id.trim()));
            List<Image> images = imageRepository.findByProductId(Long.parseLong(id.trim()));
            List<String> list = new ArrayList<>();
            if(images != null){
                list = images.stream().map(item ->{
                    String url = item.getUrl();
                    return url;
                }).collect(Collectors.toList());
            }
            if(product!= null && list != null && !list.isEmpty()){
                ProductAdminAddRequest response = ProductAdminAddRequest.builder().productName(product.getProductName())
                        .quantity(product.getQuantity()).cost(product.getCost()).shortDescription(product.getShortDescription())
                        .description(product.getDescription()).colorId(product.getColor().getId())
                        .discountId(product.getDiscount().getId()).sizeId(product.getSize().getId()).categoryId(product.getCategory().getId())
                        .urlImg(list).build();
                return BaseResponse.builder().data(response).message("request success").status(HttpStatus.OK.value()).build();
            }

            return BaseResponse.builder().message("request failed").status(HttpStatus.BAD_REQUEST.value()).build();
        }catch (Exception ex){
            return BaseResponse.builder().message("request failed").status(HttpStatus.BAD_REQUEST.value()).build();

    @Override

    public BaseResponse<?> findProductForHome(com.t3h.ecommerce.dto.request.PageRequest pageRequest) {
        if(pageRequest.getPage() <=0 || pageRequest.getPageSize() <= 0){
            return BaseResponse.builder().message("request bad").status(HttpStatus.BAD_REQUEST.value()).build();
        }
        try {
            Pageable pageable = PageRequest.of(pageRequest.getPage()-1,pageRequest.getPageSize());

            Page<Product> page = repository.findProductForHome(pageable);
            if (page==null) return BaseResponse.builder().message("request bad").status(HttpStatus.BAD_REQUEST.value()).build();

            List<ProductHomeDTO> list = page.getContent().stream().map(ProductHomeDTO::new).collect(Collectors.toList());

            return BaseResponse.builder().totalRecords(page.getTotalElements()).data(list).message("request success").status(200).build();
        }catch (Exception e){
            return BaseResponse.builder().message("request bad").status(HttpStatus.BAD_REQUEST.value()).build();

        }
    }
}
