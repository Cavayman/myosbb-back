import {Injectable} from "@angular/core";
import {Http, Response, Headers} from "@angular/http";
import {Observable} from "rxjs/Observable";
import {User} from "../../shared/models/User";
import ApiService = require("../../shared/services/api.service");

@Injectable()
export class LoginService {
    token:string;
    private _pathUrl = ApiService.serverUrl;
    currentUser:User;
    client = {
        'client_pass': '123456',
        'client_id': 'clientapp',
        'grant_type': 'password',
        'scope': 'read%20write'
    };

    constructor(private http:Http) {
    }

    //Sending credentials{username ,password for getting token}
    sendCredentials(model) {
        console.log('Authentication pending...');

        let tokenUrl = this._pathUrl + "/oauth/token";
        var data = 'username=' + encodeURIComponent(model.username) + '&password='
            + encodeURIComponent(model.password) + '&grant_type=password';

        return this.http.post(tokenUrl, data);
    }

    //sends token to SERVERS PROTECTED RESOURCES if THIS ONE WILL PASS EVERYTHING IS WORKING
    sendToken():Observable<any> {
        let userUrl = this._pathUrl + "/restful/user/getCurrent";
        return this.http.get(userUrl);
    }


    private extractData(res:Response) {
        let body = res.json();
        return body.data || {};
    }

    //cheking is there in localstorage data
    checkLogin():boolean {
        if ((localStorage.getItem("access_token") != null) && (localStorage.getItem("access_token") != "")) {
            return true;
        }
        else
            return false;
    }

    //erasing everything from  local storage
    logOut() {
        localStorage.clear();
        alert("You just log out");
    }

    //gets UserName from  localStorage
    getUserName() {
        return localStorage.getItem("currentUserName");
    }
    
    public static decodeAccessToken(access_token:string) {
        return JSON.parse(window.atob(access_token.split('.')[1]));
    }
}
