import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { LoginDTO } from 'src/models/login.dto';
import { LoginService } from '../login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent {
  username: string;
  password: string;

  constructor(
    private loginService: LoginService,
    private cookieService: CookieService,
    private router: Router
  ) {
    (this.username = ''), (this.password = '');
  }

  login(): void {
    const loginDTO: LoginDTO = {
      username: this.username,
      password: this.password,
    };

    this.loginService.login(loginDTO).subscribe(
      (response: any) => {
        // Handle the response from the backend
        console.log('Login successful!', response);
        const jwtToken = response.token;
        this.cookieService.set('jwtToken', jwtToken);
        this.router.navigate(['/']);
      },
      (error: any) => {
        // Handle any errors
        console.error('Login failed!', error);
      }
    );
  }
}
