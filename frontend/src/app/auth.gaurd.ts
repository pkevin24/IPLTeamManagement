import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';

@Injectable({
  providedIn: 'root',
})
export class AuthGuard implements CanActivate {
  constructor(private router: Router) {}

  canActivate(): boolean {
    const jwt = sessionStorage.getItem('jwt');
    if (jwt) {
      return true; // Allow access to the home page if a valid JWT is present
    } else {
      this.router.navigate(['/login']); // Redirect to the login page if JWT is missing
      return false;
    }
  }
}
