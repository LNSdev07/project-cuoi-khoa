import { Component, OnInit } from '@angular/core';
import { MessageService } from 'primeng/api';
import { DialogService, DynamicDialogRef } from 'primeng/dynamicdialog';
import { CreateOrEditQuanLyKhachHangComponent } from './create-or-edit-quan-ly-khach-hang/create-or-edit-quan-ly-khach-hang.component';

@Component({
  selector: 'app-quan-ly-khach-hang',
  templateUrl: './quan-ly-khach-hang.component.html',
  styleUrls: ['./quan-ly-khach-hang.component.scss']
})
export class QuanLyKhachHangComponent implements OnInit {
  ref: DynamicDialogRef = new DynamicDialogRef;
  
  customers: any;
  totalRecords=0;
  isLoading = false;

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
  ){

  }

  ngOnInit(): void {
      this.customers = this.fakeData();
      this.totalRecords = this.customers.length;
  }
  isCollapseFilter = false;
  isEnterpriseTab = true;

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
