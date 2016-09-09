import {Component, OnInit} from '@angular/core'
import {User} from '../../../shared/models/User.ts';
import {Router} from '@angular/router';
import {REACTIVE_FORM_DIRECTIVES, FormBuilder, Validators} from '@angular/forms';
import {CurrentUserService} from "../../../shared/services/current.user.service";
import {Http,Headers}  from "@angular/http";
import ApiService = require("../../../shared/services/api.service");
@Component({
    selector: 'my-user-profile',
    templateUrl: 'src/app/user/profile/profile.html',
    directives: [REACTIVE_FORM_DIRECTIVES],
    styleUrls: ['src/app/user/profile/profile.css'],

})
export class ProfileComponent implements OnInit {
    currentUser:User;
    private expToken:string;
    private _pathUrl = ApiService.serverUrl;

    constructor(private router:Router, private _currentUser:CurrentUserService,private http:Http) {
        console.log('constructore');
        this.currentUser = _currentUser.getUser();
        this.currentUser.birthDate = new Date(this.currentUser.birthDate).toLocaleDateString();
        this.expToken = localStorage.getItem('expires_in');
        this.expToken = new Date(parseInt(this.expToken)).toLocaleString();
    }

    refreshToken() {
        console.log("Refreshing token");
        var url = this._pathUrl + "/oauth/token";
        var headers = new Headers();
        headers.append('Authorization', `Basic Y2xpZW50YXBwOjEyMzQ1Ng==`);
        headers.append('Accept', `application/json`);
        headers.append('Content-Type', `application/x-www-form-urlencoded`);
        var data = 'grant_type=refresh_token&refresh_token=' + encodeURIComponent(localStorage.getItem('refresh_token'));
        console.log("Headers have been formed");
        this.http.post(url, data, {headers: headers}).subscribe(
            data => {
                this.tokenParseInLocalStorage(data.json());
                this.expToken = localStorage.getItem('expires_in');
                this.expToken = new Date(parseInt(this.expToken)).toLocaleString();

            },
            err => {
                console.log(err);
            }
        );
    }

    tokenParseInLocalStorage(data:any) {
        localStorage.setItem("access_token", data.access_token);
        localStorage.setItem("token_type", data.token_type);
        localStorage.setItem("expires_in", new Date().setMilliseconds(data.expires_in*1000));
        localStorage.setItem("scope", data.scope);
        localStorage.setItem("jti", data.jti);
        localStorage.setItem("refresh_token", data.refresh_token);
    }

}