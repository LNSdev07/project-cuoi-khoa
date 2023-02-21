import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { TokenService } from '../token-service/token.service';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(private tokenService: TokenService) {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    console.log('Đã đính token(JWT) vào request!') 

    let authRequest = request;
    let token = this.tokenService.getToken();
    if(token != null){
      authRequest = request.clone({headers: request.headers.set('Authorization', 'Bearer '+token)})
    }
    return next.handle(request);
  }
}
