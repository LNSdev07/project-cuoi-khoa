import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { FilterDate } from '../common/filter-date.model';
import { PageRequest } from '../common/page-request.model';

@Component({
  selector: 'app-quan-ly-color',
  templateUrl: './quan-ly-color.component.html',
  styleUrls: ['./quan-ly-color.component.scss']
})
export class QuanLyColorComponent implements OnInit {
       FilterForm!: FormGroup
       
       selectedColor =[];
       colors =[];
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

    constructor(private fb: FormBuilder){

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


      reloadTable(e: any){
        this.paginator.page = e.first==0? 1: (e.first/e.rows +1);
        this.paginator.pageSize = e.rows;
        this.paginator.sortBy = e.sortField;
        this.paginator.condition = e.sortOrder === 1 ? 'desc' : 'asc';
        
        console.log(this.paginator)
    
        // this.getData();
      }

      reset(){

      }
      search(){

      }

      createOrEdit(){
        
      }

      delete(){

      }
}
