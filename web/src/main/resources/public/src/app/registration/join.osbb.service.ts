import {Injectable} from "@angular/core";
import {Http,Headers} from "@angular/http";
import {Osbb} from "../../shared/models/osbb";
import {Observable} from 'rxjs/Observable';
import ApiService = require("../../shared/services/api.service");
import "rxjs/add/operator/toPromise";
@Injectable()
export class JoinOsbbService{
    private _pathUrl=ApiService.serverUrl + '/join/osbb';

    constructor(private http:Http){
    }

    getAllOsbb(): Promise<IOsbb[]> {
        return this.http.get(this._pathUrl)
                 .toPromise()
                 .then(res => res.json())
                 .catch(this.handleError);
    }
    
    private handleError(error: any):Promise<any> {
        console.log('HandleError', error);
        return Promise.reject(error.message || error);
    }


}
