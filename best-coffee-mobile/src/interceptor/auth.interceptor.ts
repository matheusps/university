import { Injectable } from '@angular/core';
import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor } from '@angular/common/http';

import { AuthService } from '../services/authentication.service';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class AuthenticationInterceptor implements HttpInterceptor {
  
    constructor(private authService: AuthService) {};

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        if( this.authService.isAuthenticated() ) {
            request = request.clone({
                setHeaders: {
                Authorization: `Bearer ${this.authService.getUser().token}`
                }
            });
        }
        return next.handle(request);
    };
}