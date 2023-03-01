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

  constructor(
    public dialogService: DialogService,
    private messageService: MessageService,
  ){

  }

  ngOnInit(): void {
   
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
}
