import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { SignupDTO } from 'src/models/signup.dto';
import { SignupService } from '../signup.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css'],
})
export class SignupComponent {
  username: string;
  password: string;

  constructor(private signupService: SignupService, private router: Router) {
    (this.username = ''), (
    this.password = '');
  }

  signup(): void {
    const signupData: SignupDTO = {
      username: this.username,
      password: this.password,
    };
    this.signupService.signup(signupData).subscribe(
      (response:any) => {
        // Handle the response from the backend
        console.log(response)
        if(response.token!="sucessful"){
          alert("Signuo failed");
        }
        else
        {
        console.log('Signup successful!', response);
        this.router.navigate(['/login']);
        }


      },
      (error: any) => {
        // Handle any errors
        console.error('Login failed!', error);
      }
    );
  }
}
