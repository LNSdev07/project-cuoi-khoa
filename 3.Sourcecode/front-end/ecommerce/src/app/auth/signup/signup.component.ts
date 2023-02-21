import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import { SignUpService } from './service/sign-up.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss']
})
export class SignupComponent implements OnInit {
  formRegister!: FormGroup;

  constructor(private fb: FormBuilder,
              private signUpService: SignUpService) { }

  ngOnInit(): void {
    this.buildForm();
  }

  buildForm(){
    this.formRegister = this.fb.group({
      username: ['', [Validators.required]],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]],
      password_again: ['', [Validators.required, Validators.minLength(6)]]
    });
  }

  get f(){
    return this.formRegister.controls;
  }



}
