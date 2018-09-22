import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import 'rxjs/add/operator/map';

import { REQUESTS } from '../configs/requests';
import { UnregisteredUser } from '../model/unregistered-user';
import { RegisteredUser } from '../model/registered-user';

@Injectable()
export class UserService{

    private url: string = REQUESTS.URL + '/user';

    constructor(private http: HttpClient) {};

    /**
     * Register a new user
     * @param unregisteredUser 
     */
    register(unregisteredUser: UnregisteredUser){
        return this.http.post(`${this.url}/register`, unregisteredUser)
            .map( (registeredUser: RegisteredUser) => {
                return registeredUser;
        });
    };

}