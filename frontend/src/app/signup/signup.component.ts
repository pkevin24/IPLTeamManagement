import { Component } from '@angular/core';
import { SignupDTO } from 'src/models/signup.dto';
import { SignupService } from '../signup.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css'],
})
export class SignupComponent {
  name: string;
  username: string;
  password: string;
  constructor(private signupService: SignupService) {
    this.name = '';
    (this.username = ''), (this.password = '');
  }

  signup(): void {
    const signupData: SignupDTO = {
      name: this.name,
      username: this.username,
      password: this.password,
    };
    this.signupService.signup(signupData).subscribe(
      (response) => {
        // Handle the response from the backend
        console.log('Signup successful!', response);
      },
      (error) => {
        // Handle any errors
        console.error('Signup failed!', error);
      }
    );
  }
}
