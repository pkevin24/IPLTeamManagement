import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SignupDTO } from 'src/models/signup.dto';

@Injectable({
  providedIn: 'root',
})
export class SignupService {
  constructor(private http: HttpClient) {}

  signup(signupData: SignupDTO): Observable<any> {
    const url = 'http://localhost:8080/jwt/register';
    console.log(signupData);
    return this.http.post(url, signupData);
  }
}
