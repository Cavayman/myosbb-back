import {Component,OnInit} from '@angular/core'


import {userItem} from './userItem.ts';
import 

import {HTTP_PROVIDERS, Http} from "@angular/http";
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/toPromise';

@Component ({
	selector:'my-users',
	templateUrl:'src/app/user/users/users.table.html',
	providers:[HTTP_PROVIDERS],
	styleUrls:['src/app/user/users/users.css']
})
export class UsersComponent {

userItem:userItem[];	

	 constructor(private http:Http) {
        this.http.get('./src/app/user/users/data.json')
               .map(response => response.json())
               .subscribe((data) => {this.userItem = data});
               console.log("USERSAREHERE!!!");
    }
}