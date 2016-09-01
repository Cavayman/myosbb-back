import {Injectable} from "@angular/core";
import {Http, Response, Headers} from "@angular/http";
import {Observable} from "rxjs/Observable";
import {User} from "../../shared/models/User";
import ApiService = require("../../shared/services/api.service");
import {routes} from "../app.routes";

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
        
    }

    //gets UserName from  localStorage
    getUserName() {
        return localStorage.getItem("currentUserName");
    }

    validateEmail(data){
        let validate=this._pathUrl+"/validEmail";
        let headers=new Headers({'Content-Type':'application/json'});
        return this.http.post(validate, data,{headers:headers});
    }
    sendPassword(data){
        let url=this._pathUrl+"/sendEmailMail";
        let headers=new Headers({'Content-Type':'application/json'});
        return this.http.post(url, data,{headers:headers});
    }
    
    public static decodeAccessToken(access_token:string) {
        return JSON.parse(window.atob(access_token.split('.')[1]));
    }
}
