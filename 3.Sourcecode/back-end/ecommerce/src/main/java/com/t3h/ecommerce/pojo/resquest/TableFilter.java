package com.t3h.ecommerce.pojo.resquest;

import lombok.Data;
import org.hibernate.mapping.Collection;

import java.util.Collections;
import java.util.List;
@Data
public class TableFilter {
    private String direction ;
    private String property ;
    private List<String> filterColor = Collections.emptyList();
    private List<String> filterSize = Collections.emptyList();
}
