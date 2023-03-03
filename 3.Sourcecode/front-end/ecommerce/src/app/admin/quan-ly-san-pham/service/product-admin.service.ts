import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'environments/environment';
import { catchError, Observable } from 'rxjs';
import { HandleErrorService } from 'src/app/common/handle-error/handle-error.service';
import { ProductAdminReQuestModel } from '../model/product-admin-request.model';
import { ProductAdminResponse } from '../model/product-admin-response.model';

@Injectable({
  providedIn: 'root'
})
export class ProductAdminService {

  private URL_FIND = environment.API_LOCAL+'/admin/product';

  constructor(private http: HttpClient,
              private handleErr: HandleErrorService) { }
  

  public findProduct(request: ProductAdminReQuestModel):Observable<ProductAdminResponse>{
    return this.http.post<ProductAdminResponse>(this.URL_FIND, request).pipe(
      catchError(this.handleErr.handleError)
    );
  } 
}
