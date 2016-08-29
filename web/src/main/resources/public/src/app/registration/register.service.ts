import {Injectable} from "@angular/core";
import {HTTP_PROVIDERS, Http,Headers,Response} from "@angular/http";
import {User} from "../../shared/models/User";
import {Observable} from 'rxjs/Observable';
import ApiService = require("../../shared/services/api.service");

@Injectable()
export class RegisterService{

    private _pathUrl=ApiService.serverUrl + '/registration';

    constructor(private http:Http){
    }
    sendUser(user:User){

        console.log(user);
    return this.http.post(this._pathUrl,JSON.stringify(user));
    }

    
}