import { Component } from '@angular/core';
import { SignupDTO } from 'src/models/signup.dto';
import { SignupService } from '../signup.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css'],
})
export class SignupComponent {
  constructor(private signupService: SignupService) {}

  signup(): void {
    const signupData: SignupDTO = {
      name: '',
      username: '',
      password: '',
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
