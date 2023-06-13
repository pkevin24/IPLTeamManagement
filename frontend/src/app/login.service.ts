import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { LoginDTO } from 'src/models/login.dto';

@Injectable({
  providedIn: 'root',
})
export class LoginService {
  constructor(private http: HttpClient) {}
  login(loginDTO: LoginDTO): Observable<any> {
    const url = 'http://localhost:8080/jwt/login'; // Replace with your backend API URL
    console.log(loginDTO.password);
    return this.http.post(url, loginDTO);
  }
}
