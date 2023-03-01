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

  constructor(private fb: FormBuilder,
              private signUpService: SignUpService,
              private uploadFileService: UpLoadFileService) { }

  ngOnInit(): void {
    this.buildForm();
  }
  
  isLoading = false;

  buildForm(){
    this.formRegister = this.fb.group({
      username: ['', [Validators.required]],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]],
      password_again: ['', [Validators.required, Validators.minLength(6)]],
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
