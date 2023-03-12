import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AdminRoutingModule } from './admin-routing.module';
import { AdminComponent } from './admin.component';
import { ProfileAdminComponent } from './profile-admin/profile-admin.component';
import { QuanLyColorComponent } from './quan-ly-color/quan-ly-color.component';
import { QuanLyDanhMucComponent } from './quan-ly-danh-muc/quan-ly-danh-muc.component';
import { QuanLyDiscountComponent } from './quan-ly-discount/quan-ly-discount.component';
import { QuanLyDoanhThuComponent } from './quan-ly-doanh-thu/quan-ly-doanh-thu.component';
import { QuanLyKhachHangComponent } from './quan-ly-khach-hang/quan-ly-khach-hang.component';
import { QuanLySanPhamComponent } from './quan-ly-san-pham/quan-ly-san-pham.component';
import { QuanLySizeComponent } from './quan-ly-size/quan-ly-size.component';
import { SharedModule } from '../shared/shared.module';
import { CreateOrEditProductComponent } from './quan-ly-san-pham/modal/create-or-edit-product/create-or-edit-product.component';
import { CreateOrEditCategoryComponent } from './quan-ly-danh-muc/modal/create-or-edit-category/create-or-edit-category.component';

@NgModule({
  declarations: [
    AdminComponent,
    ProfileAdminComponent,
    QuanLyColorComponent,
    QuanLyDanhMucComponent,
    QuanLyDiscountComponent,
    QuanLyDoanhThuComponent,
    QuanLyKhachHangComponent,
    QuanLySanPhamComponent,
    QuanLySizeComponent,
    CreateOrEditProductComponent,
    CreateOrEditCategoryComponent
  ],
    imports: [
        CommonModule,
        AdminRoutingModule,
        SharedModule,
    ]
})
export class AdminModule { }
