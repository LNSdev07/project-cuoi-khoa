import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MessageService } from 'primeng/api';
import { DialogService, DynamicDialogRef } from 'primeng/dynamicdialog';
import { PopupConfirmComponent } from 'src/app/shared/popup-confirm/popup-confirm.component';
import { FilterDate } from '../common/filter-date.model';
import { PageRequest } from '../common/page-request.model';
import { CreateOrEditColorComponent } from './modal/create-or-edit-color/create-or-edit-color.component';
import { ColorRequestModel } from './model/color-request.model';
import { ColorModel } from './model/color.model';
import { ColorService } from './service/color.service';

@Component({
  selector: 'app-quan-ly-color',
  templateUrl: './quan-ly-color.component.html',
  styleUrls: ['./quan-ly-color.component.scss']
})
export class QuanLyColorComponent implements OnInit {
  
       ref: DynamicDialogRef = new DynamicDialogRef;
       FilterForm!: FormGroup
       
       selectedColor!: ColorModel[];
       colors: any;
       totalRecords =0;

        // de phan trang va sap xep
    paginator: PageRequest={
      page: 1,
      pageSize: 5,
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

    constructor(private fb: FormBuilder,
      public dialogService: DialogService,
      private messageService: MessageService,
      private colorService: ColorService){

    }

       ngOnInit(): void {
        this.builForm();
        
      }

      builForm(){
        this.FilterForm = this.fb.group({
          colorName: [''],
          colorCode: [''],
          createdDate: [0],
          updatedDate: [0]
        })
      }

      getInput(): ColorRequestModel{

          this.filterDate.createdDateStart = this.FilterForm.controls['createdDate'].value[0]? this.FilterForm.controls['createdDate'].value[0].getTime():0;
          this.filterDate.createdDateEnd = this.FilterForm.controls['createdDate'].value[1]?this.FilterForm.controls['createdDate'].value[1].getTime():0;
          this.filterDate.updatedDateStart = this.FilterForm.controls['updatedDate'].value[0]? this.FilterForm.controls['updatedDate'].value[0].getTime():0;
          this.filterDate.updatedDateEnd = this.FilterForm.controls['updatedDate'].value[1]?this.FilterForm.controls['updatedDate'].value[1].getTime():0;

          const input : ColorRequestModel ={
            filterDate: this.filterDate,
            pageRequest: this.paginator,
            colorName: this.FilterForm.controls['colorName'].value,
            colorCode: this.FilterForm.controls['colorCode'].value
          }
          
          return input;
      }


      reloadTable(e: any){
        this.paginator.page = e.first==0? 1: (e.first/e.rows +1);
        this.paginator.pageSize = e.rows;
        this.paginator.sortBy = e.sortField;
        this.paginator.condition = e.sortOrder === 1 ? 'desc' : 'asc';
        
        console.log(this.paginator)
    
        this.getData();
      }

      getData(){
        const input = this.getInput();
        this.colorService.findColor(input).subscribe(res =>{
          this.colors = res.data;
          this.totalRecords = res.totalRecords;
        })
      }

      reset(){
        this.builForm();
        this.paginator ={
          page: 1,
          pageSize: 5,
          sortBy: '',
          condition: ''
        }
        this.getData();
      }
      search(){
        this.getData();
      }

      createOrEdit(color?: any){
        this.ref = this.dialogService.open(CreateOrEditColorComponent, {
          header: color? 'Chi tiết ' : 'Thêm mới ',
          width: '60%',
          height:'60%',
          contentStyle: { 'padding-bottom': '0','height':'100%' },
          baseZIndex: 10000,
          data: { color: color },
        });
        this.ref.onClose.subscribe(() => {
          this.messageService.add({
            severity: 'success',
            summary: 'Success',
            detail: '',
            life: 3000,
          });
        });
      }

      delete(){
        const confirm = this.dialogService.open(PopupConfirmComponent, {
          showHeader: false,
          baseZIndex: 10000,
          data: {
            title: 'Bạn có chắc muốn xóa Color không ?',
            content: '',
            status: 1
          }
        });
    
        confirm.onClose.subscribe(res => {
              if(this.selectedColor.length != 0 && res === 'yes'){
                const Ids  = this.selectedColor.map(ele => ele.id);
                this.colorService.deleteColor(Ids).subscribe(res =>{
                  console.log(res);
                  if(res.status === 200){
                      this.messageService.add({
                        severity: 'success',
                        summary: 'Success',
                        detail: '',
                        life: 3000,
                      });
                      this.selectedColor =[];
                      this.getData();
                  }
                  else{
                    this.messageService.add({
                      severity: 'error',
                      summary: 'fail',
                      detail: '',
                      life: 3000,
                    });
                  }
                })
              }
        })

      }
}
