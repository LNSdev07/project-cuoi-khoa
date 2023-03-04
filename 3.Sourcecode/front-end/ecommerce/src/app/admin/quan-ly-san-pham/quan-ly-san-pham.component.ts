import { Component, OnInit } from '@angular/core';
import { MessageService } from 'primeng/api';
import { DialogService, DynamicDialogRef } from 'primeng/dynamicdialog';
import { FilterDate } from '../common/filter-date.model';
import { PageRequest } from '../common/page-request.model';
import { CreateOrEditQuanLyKhachHangComponent } from '../quan-ly-khach-hang/create-or-edit-quan-ly-khach-hang/create-or-edit-quan-ly-khach-hang.component';
import { ProductAdminReQuestModel } from './model/product-admin-request.model';
import { ProductAdminService } from './service/product-admin.service';

@Component({
  selector: 'app-quan-ly-san-pham',
  templateUrl: './quan-ly-san-pham.component.html',
  styleUrls: ['./quan-ly-san-pham.component.scss']
})
export class QuanLySanPhamComponent implements OnInit {
  ref: DynamicDialogRef = new DynamicDialogRef;
  
  products: any;
  totalRecords=0;
  isLoading = false;
  
  // de phan trang va sap xep
  paginator: PageRequest={
    page: 1,
    pageSize: 10,
    sortBy: '',
    condition: ''
  }
  // loc theo thoi gian
  filterDate: FilterDate ={
    createdDateStart: 0,
    createdDateEnd: 0,
    updatedDateStart: 0,
    updatedDateEnd: 0
  }



  gender =[
    {code: 0, name: 'Nam'},
    {code: 1, name: 'Nữ'}
  ]

  status = [
    {code: 1, name: 'Enable'},
    {code: 0, name: 'Disable'},
  ]

  constructor(
    public dialogService: DialogService,
    private messageService: MessageService,
    private productAdminService: ProductAdminService
  ){

  }

  ngOnInit(): void {
      const input = this.getInput();
      console.log(input)
      this.productAdminService.findProduct(input).subscribe(res =>{
        this.totalRecords = res.totalRecords;
        this.products = res.data;
        console.log(this.products)
      })
      
  }
  isCollapseFilter = false;
  isEnterpriseTab = true;


  getInput(){
    const input: ProductAdminReQuestModel ={
      filterDate: this.filterDate,
      pageRequest: this.paginator,
      productName: '',
      quantity: 0,
      cost: 0,
      categoryId: 0
    }
    return input;
  }

  onClickCollapseFilter(event:any) {
    this.isCollapseFilter = event.collapsed;
  }

  addOrEdit(severity?: any){
    this.ref = this.dialogService.open(CreateOrEditQuanLyKhachHangComponent, {
      header: severity ? 'Detail' : 'Add new Severity',
      width: '50%',
      height:'100%',
      contentStyle: { 'padding-bottom': '0','height':'100%' },
      baseZIndex: 10000,
      data: { severity: severity },
    });
    this.ref.onClose.subscribe(() => {
      this.messageService.add({
        severity: 'success',
        summary: 'Success',
        detail: '',
        life: 3000,
      });
      // this.getData();
    });
  }
  
  fakeData(){
    return [
    {id : 1,fullName: 'Lai Ngoc Son 1' ,userName: 'sonln1' , status:1, address:'Thanh hoa', email:'son@gmail.com', 
    phoneNumber: '0977822938', gender: 1, createdDate: '12/02/2023', updatedDate: '12/03/2023'},
    {id : 2,fullName: 'Lai Ngoc Son 2',userName: 'sonln2', status:0, address:'Thanh hoa', email:'son@gmail.com', 
    phoneNumber: '0977822938', gender: 1, createdDate: '12/02/2023', updatedDate: '12/03/2023'},
    {id : 3,fullName: 'Lai Ngoc Son 3',userName: 'sonln3', status:1, address:'Thanh hoa', email:'son@gmail.com', 
    phoneNumber: '0977822938', gender: 1, createdDate: '12/02/2023', updatedDate: '12/03/2023'},
    {id : 4,fullName: 'Lai Ngoc Son 4',userName: 'sonln4', status:0, address:'Thanh hoa', email:'son@gmail.com', 
    phoneNumber: '0977822938', gender: 1, createdDate: '12/02/2023', updatedDate: '12/03/2023'},
    ]
  }
}
