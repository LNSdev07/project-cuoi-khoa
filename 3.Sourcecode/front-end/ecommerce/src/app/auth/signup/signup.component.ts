import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import { forkJoin } from 'rxjs';
import { UpLoadFileService } from 'src/app/common/upload-file/up-load-file.service';
import { SignUpService } from './service/sign-up.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss']
})
export class SignupComponent implements OnInit {
  formRegister!: FormGroup;
  avatarDefaut = 'https://thumbs.dreamstime.com/b/default-avatar-profile-icon-vector-social-media-user-portrait-176256935.jpg';
  isLoading = false;
  gender =[
    {code: 1, name: 'Nam'},
    {code: 2, name: 'Nữ'}
  ]
  constructor(private fb: FormBuilder,
              private signUpService: SignUpService,
              private uploadFileService: UpLoadFileService) { }
  ngOnInit(): void {
    this.buildForm();
  }
  
  buildForm(){
    this.formRegister = this.fb.group({
      username: [null, Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]],
      fullName:['', Validators.required],
      address:['', Validators.required],
      gender:[null, Validators.required],
      phoneNumber:[null, Validators.required]
    });
  }

  get f(){
    return this.formRegister.controls;
  }
  
  chooseFile(event: any){
    this.isLoading = true;
    const selectedFile = event.files[0]
    this.uploadFileService.uploadFileAndGetDownloadUrl(selectedFile).subscribe(
      url => {
        this.avatarDefaut = url;
        this.isLoading = false;
      },
      error => console.error(error)
    );

  }
  
  


}
