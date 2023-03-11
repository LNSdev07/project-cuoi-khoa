package com.t3h.ecommerce.utils;


import com.t3h.ecommerce.dto.request.admin_product.ProductAdminDTO;
import com.t3h.ecommerce.entities.core.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExportConfig {
    private int sheetIndex;
    private int startRow;
    private Class dataClazz;
    private List<CellConfig> cellExportConfigList;

    public static final ExportConfig customerExport;
    static {
        customerExport = new ExportConfig();
        customerExport.setSheetIndex(0);
        customerExport.setStartRow(1);
        customerExport.setDataClazz(User.class);
        List<CellConfig> customerCellConfig = new ArrayList<>();
        customerCellConfig.add(new CellConfig(0, "id"));
        customerCellConfig.add(new CellConfig(1, "username"));
        customerCellConfig.add(new CellConfig(2, "fullName"));
        customerCellConfig.add(new CellConfig(3, "phoneNumber"));
        customerCellConfig.add(new CellConfig(4, "gender"));
        customerCellConfig.add(new CellConfig(5, "status"));
        customerCellConfig.add(new CellConfig(6, "address"));
        customerCellConfig.add(new CellConfig(7, "email"));
        customerCellConfig.add(new CellConfig(8, "avatar"));
        customerExport.setCellExportConfigList(customerCellConfig);
    }
    public static final ExportConfig productExport ;
    static {
        productExport = new ExportConfig();
        productExport.setSheetIndex(0);
        productExport.setStartRow(1);
        productExport.setDataClazz(ProductAdminDTO.class);
        List<CellConfig> productCellConfig = new ArrayList<>();
        productCellConfig.add(new CellConfig(0, "id"));
        productCellConfig.add(new CellConfig(1, "productName"));
        productCellConfig.add(new CellConfig(2, "shortDescription"));
        productCellConfig.add(new CellConfig(3, "cost"));
        productCellConfig.add(new CellConfig(4, "quantity"));
        productCellConfig.add(new CellConfig(5, "categoryId"));
        productCellConfig.add(new CellConfig(6, "createdDate"));
        productCellConfig.add(new CellConfig(7, "updatedDate"));
        productExport.setCellExportConfigList(productCellConfig);
    }
}
