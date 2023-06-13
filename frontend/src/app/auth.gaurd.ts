import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';

@Injectable({
  providedIn: 'root',
})
export class AuthGuard implements CanActivate {
  constructor(private router: Router, private cookieService: CookieService) {}

  canActivate(): boolean {
    const jwt = this.cookieService.get('jwtToken');
    if (jwt) {
      return true; // Allow access to the home page if a valid JWT is present
    } else {
      this.router.navigate(['/login']); // Redirect to the login page if JWT is missing
      return false;
    }
  }
}
