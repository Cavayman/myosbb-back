import {Injectable} from "@angular/core";
import {HTTP_PROVIDERS, Http,Headers,Response} from "@angular/http";
import {User} from "../user/users/User";
import {Observable} from 'rxjs/Observable';
import ApiService = require("../../shared/services/api.service");

@Injectable()
export class RegisterService{

    private _pathUrl=ApiService.serverUrl + '/registration';

    constructor(private http:Http){
    }
    sendUser(user:User){
        let headers = new Headers();
        headers.append('Content-Type', 'application/json');
        console.log(user);
    return this.http.post(this._pathUrl,JSON.stringify(user),{headers:headers});
    }
}