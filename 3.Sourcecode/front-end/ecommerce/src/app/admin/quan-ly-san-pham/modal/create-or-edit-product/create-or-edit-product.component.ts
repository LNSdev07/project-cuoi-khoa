import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { DynamicDialogRef } from 'primeng/dynamicdialog';
import { UpLoadFileService } from 'src/app/common/upload-file/up-load-file.service';
import { FormAddProductModel } from '../../model/form-add-product.model';

@Component({
  selector: 'app-create-or-edit-product',
  templateUrl: './create-or-edit-product.component.html',
  styleUrls: ['./create-or-edit-product.component.scss']
})
export class CreateOrEditProductComponent implements OnInit {
  AddForm!: FormGroup
  formAddProduct!: FormAddProductModel
  arrUrlImg :string[]=[]

  constructor(public ref: DynamicDialogRef,
            private uploadFileService: UpLoadFileService,
            private fb: FormBuilder){}
  ngOnInit(): void {
    this.builForm();
  }

  getInput(): FormAddProductModel{
    const input: FormAddProductModel ={
      productName: this.AddForm.controls['productName'].value ?? '',
      quantity: this.AddForm.controls['quantity'].value ?? 0,
      cost: this.AddForm.controls['cost'].value ?? 0,
      shortDescription: this.AddForm.controls['shortDescription'].value ?? '',
      description: this.AddForm.controls['description'].value ?? '',
      image: this.arrUrlImg,
      color: [],
      size: [],
      discount: []
    };
    return input;
  }
  
  isLoading = false
  
  onClose(){
    this.ref.close();
  }

  get f(){
    return this.AddForm.controls;
  }

  onSave(){
    // this.ref.close();
    console.log(this.getInput())
  }

  builForm(){
    this.AddForm = this.fb.group({
      productName: [null, [Validators.compose([Validators.required])]],
      quantity: [null,  [Validators.compose([Validators.required])]],
      cost: [null,  [Validators.compose([Validators.required])]],
      categoryId: [null, [Validators.compose([Validators.required])]],
      shortDescription: [null,[Validators.compose([Validators.required])]],
      description: [null,[Validators.compose([Validators.required])]],
      discount:[null,  [Validators.compose([Validators.required])]],
      size: [null,  [Validators.compose([Validators.required])]],
      color: [null,  [Validators.compose([Validators.required])]]
    })
  }

  chooseFile(e?: any){
    let arr =[];
    let arrRes: string[] =[]
    if(e) arr = e;
    this.isLoading = true;
    arr = Array.from(arr.files);
    for(let i=0 ;i < arr.length; i++){
      const selectedFile = arr[i];
      if( selectedFile instanceof File){
        this.uploadFileService.uploadFileAndGetDownloadUrl(selectedFile, 'product').subscribe(
        url => {
          arrRes.push(url);
         if(arrRes.length == arr.length) this.isLoading = false;
        },
        error => console.error(error)
        );
       }
      else{
        console.error("Invalid file type!");
      }
    }
    this.arrUrlImg = arrRes;
  }
}
