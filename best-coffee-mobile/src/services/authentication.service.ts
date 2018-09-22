import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';

import { REQUESTS } from '../configs/requests';
import { UserCredentials } from '../model/user-credentials';
import { AuthenticatedUser } from '../model/authenticated-user';

@Injectable()
export class AuthService{

    private url: string = REQUESTS.URL + '/auth/login';
    private httpOptions = {headers: new HttpHeaders({ 'Content-Type': 'application/json' })};

    constructor(private http: HttpClient) {}

    /** Login a user
     * @param email 
     * @param password 
     */
    login (credentials: UserCredentials) : Observable<boolean> {

        return this.http.post<any>( this.url, credentials, this.httpOptions)
            .map( (user: AuthenticatedUser) => {
                if (user && user.token) {
                    localStorage.setItem('bestCoffeeUser', JSON.stringify(user));
                    return true;
                }
                return false;
            })
    }

    /**
     * Return if a user is authenticated
     */
    isAuthenticated() : boolean {
        const user = this.getUser();
        return (user && user.token) ? true : false;
    }

    /**
     * Get user from localstorage
     * @returns {string}
     */
    getUser() : AuthenticatedUser {
      return JSON.parse(localStorage.getItem('bestCoffeeUser'));
    }

    /**
     * Remove user from storage
     */
    logout() {
        localStorage.removeItem('bestCoffeeUser');
    }
}