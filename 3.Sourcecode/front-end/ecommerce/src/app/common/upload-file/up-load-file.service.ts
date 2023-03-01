import { Injectable } from '@angular/core';
import { AngularFireStorage, AngularFireStorageReference } from '@angular/fire/compat/storage';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UpLoadFileService {
  
  constructor(private storage: AngularFireStorage) { }

  uploadFileAndGetDownloadUrl(file: File): Observable<string> {
    const filePath = `uploads/${file.name}`;
    const fileRef = this.storage.ref(filePath);
    const task = this.storage.upload(filePath, file);

    return new Observable<string>(observer => {
      task.snapshotChanges().subscribe(
        snapshot => {},
        error => {
          console.error(error);
          observer.error(error);
        },
        () => {
          fileRef.getDownloadURL().subscribe(
            url => {
              observer.next(url);
              observer.complete();
            },
            error => {
              console.error(error);
              observer.error(error);
            }
          );
        }
      );
    });
  }
}
