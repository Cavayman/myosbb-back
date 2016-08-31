import {Injectable} from "@angular/core";
import {Http,Headers} from "@angular/http";
import {Osbb} from "../../shared/models/osbb";
import {Observable} from 'rxjs/Observable';
import ApiService = require("../../shared/services/api.service");

@Injectable()
export class RegisterOsbbService{
    private _pathUrl=ApiService.serverUrl + '/registration/osbb';

    constructor(private http:Http){
    }
    sendOsbb(osbb:Osbb){
    console.log(osbb);
        let headers=new Headers({'Content-Type':'application/json'});
    return this.http.post(this._pathUrl,JSON.stringify(osbb),{headers:headers});
    }

}
