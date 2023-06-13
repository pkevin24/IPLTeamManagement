import { Component } from '@angular/core';
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

  constructor(private loginService: LoginService) {
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
      },
      (error: any) => {
        // Handle any errors
        console.error('Login failed!', error);
      }
    );
  }
}
