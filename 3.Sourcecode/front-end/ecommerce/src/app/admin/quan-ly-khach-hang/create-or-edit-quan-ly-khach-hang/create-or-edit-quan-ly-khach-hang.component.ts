import { Component } from '@angular/core';
import { DialogService, DynamicDialogRef } from 'primeng/dynamicdialog';
import { PopupConfirmComponent } from 'src/app/shared/popup-confirm/popup-confirm.component';

@Component({
  selector: 'app-create-or-edit-quan-ly-khach-hang',
  templateUrl: './create-or-edit-quan-ly-khach-hang.component.html',
  styleUrls: ['./create-or-edit-quan-ly-khach-hang.component.scss']
})
export class CreateOrEditQuanLyKhachHangComponent {

  constructor( public ref: DynamicDialogRef,
               private dialogService: DialogService ){
    
  }
  onClose(){
      // this.ref.close();


      const confirm = this.dialogService.open(PopupConfirmComponent, {
        showHeader: false,
        baseZIndex: 10000,
        data: {
          title: 'Lai Ngoc son',
          content: '',
        }
      });
  
      confirm.onClose.subscribe(res => {
        console.log(res)
      })

  }
  onSave(){
     this.ref.close();
  }
}
