import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { DialogService, DynamicDialogConfig, DynamicDialogRef } from 'primeng/dynamicdialog';
import { forkJoin } from 'rxjs';
import { ColorModel } from 'src/app/admin/quan-ly-color/model/color.model';
import { CategoryModel } from 'src/app/admin/quan-ly-danh-muc/model/category.model';
import { DiscountModel } from 'src/app/admin/quan-ly-discount/model/discount.model';
import { SizeModel } from 'src/app/admin/quan-ly-size/model/size.model';
import { UpLoadFileService } from 'src/app/common/upload-file/up-load-file.service';
import { PopupConfirmComponent } from 'src/app/shared/popup-confirm/popup-confirm.component';
import { FormAddProductModel } from '../../model/form-add-product.model';
import { ProductAdminResponse } from '../../model/product-admin-response.model';
import { ProductAdminService } from '../../service/product-admin.service';

@Component({
  selector: 'app-create-or-edit-product',
  templateUrl: './create-or-edit-product.component.html',
  styleUrls: ['./create-or-edit-product.component.scss']
})
export class CreateOrEditProductComponent implements OnInit {
  AddForm!: FormGroup
  formAddProduct!: FormAddProductModel
  arrUrlImg :string[]=[]
  isDetail = false;
  product!: ProductAdminResponse
  urlImgDetail= ['']

  category= [
    {
      code: 0,
      name: ''
    }
  ] 
  color= [
    {
      code: 0,
      name: ''
    }
  ] 
  size = [
    {
      code: 0,
      name: ''
    }
  ] 
  discount= [
    {
      code: 0,
      name: ''
    }
  ] 

  constructor(public ref: DynamicDialogRef,
            private uploadFileService: UpLoadFileService,
            private productAdminService: ProductAdminService,
            private dialogService: DialogService,
            public config: DynamicDialogConfig,
            private fb: FormBuilder){}


  ngOnInit(): void {
    this.builForm();
    this.urlImgDetail =[];
    this.product = this.config.data.product;
    if(this.product){
      this.isDetail = true;
      this.builForm();
      this.setValueForm();
      this.setDisable();
    }
    forkJoin([
      this.productAdminService.getAllCategories(),
      this.productAdminService.getAllColors(),
      this.productAdminService.getAllSizes(),
      this.productAdminService.getAllDiscounts(),
   ]).subscribe(result =>{
     this.category =[];
     this.size =[];
     this.discount =[];
     this.color =[];

     result[0].data.forEach(res =>{
      this.category.push({code: res.id, name: res.categoryName});
     })
     result[1].data.forEach(res =>{
      this.color.push({code: res.id, name: res.colorName});
     });
     result[2].data.forEach(res =>{
      this.size.push({code: res.id, name: res.sizeName});
     });
    result[3].data.forEach(res =>{
      this.discount.push({code: res.id,  name: res.discountPercent});
    });
    })
  }

  edit(){
    this.config.header = 'Chỉnh sửa thông tin sản phẩm';
    this.isDetail = false;
    this.setEnable();
    }
  
  setValueForm(){
          this.productAdminService.findDetail(this.product.id).subscribe(res =>{
           if(res.status === 200){
             this.AddForm.controls['productName'].setValue(res.data.productName);
             this.AddForm.controls['quantity'].setValue(res.data.quantity);
             this.AddForm.controls['cost'].setValue(res.data.cost);
             this.AddForm.controls['shortDescription'].setValue(res.data.shortDescription);
             this.AddForm.controls['description'].setValue(res.data.description);
             this.AddForm.controls['color'].setValue(res.data.colorId);
             this.AddForm.controls['size'].setValue(res.data.sizeId);
             this.AddForm.controls['discount'].setValue(res.data.discountId);
             this.AddForm.controls['categoryId'].setValue(res.data.categoryId);
             console.log(res.data.urlImg)
             res.data.urlImg.forEach(item =>{
              this.urlImgDetail.push(item);
             })
            }
            
          })
    }

  setDisable(){
    this.AddForm.disable();
  }
  setEnable(){
    this.AddForm.enable();
  }


  getInput(): FormAddProductModel{
    const input: FormAddProductModel ={
      productName: this.AddForm.controls['productName'].value ?? '',
      quantity: this.AddForm.controls['quantity'].value ?? 0,
      cost: this.AddForm.controls['cost'].value ?? 0,
      shortDescription: this.AddForm.controls['shortDescription'].value ?? '',
      description: this.AddForm.controls['description'].value ?? '',
      urlImg: this.arrUrlImg,
      colorId: this.AddForm.controls['color'].value ?? null,
      sizeId: this.AddForm.controls['size'].value ?? null,
      discountId: this.AddForm.controls['discount'].value ?? null,
      categoryId: this.AddForm.controls['categoryId'].value??null
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
    const input = this.getInput();
    this.productAdminService.addProduct(input).subscribe(res =>{
      if(res.status == 200){
        const confirm = this.dialogService.open(PopupConfirmComponent, {
          showHeader: false,
          baseZIndex: 10000,
          data: {
            title: 'Bạn đã tạo mới sản phẩm thành công',
            content: '',
            status: 0
          }
        });
        confirm.onClose.subscribe(res => {
          location.reload();
        })
      }
    })
    
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
