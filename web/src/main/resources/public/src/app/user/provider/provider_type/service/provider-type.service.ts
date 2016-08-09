/**
 * Created by Anastasiia Fedorak  8/4/16.
 */
import {Injectable} from "@angular/core";
import {Http, Response, Headers, RequestOptions} from "@angular/http";
import {Observable} from "rxjs/Rx";
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/toPromise';
import {ProviderType} from "../../../../../shared/models/provider.type.interface";
import ApiService = require("../../../../../shared/services/api.service");


@Injectable()
export class ProviderTypeService {
   // private url = '/restful/providertype/';
    private url = ApiService.serverUrl + '/restful/providertype/';

    constructor(private _http:Http){
    }

    getProviderTypes() : Observable<any>{
        console.log("get all provider types inside service");
        console.log("sending http GET");
        return  this._http.get(this.url)
            .map(res => res.json())
            .catch((err)=>Observable.throw(err));
    }

    getProviderTypeById(id: number) :  Observable<any> {
        let headers = new Headers({'Content-Type': 'application/json'});
        let options = new RequestOptions({headers});
        return this._http.get(this.url + id)
            .map(r => r.json());
    }


    deleteProviderTypeById(providerTypeId:number) {
        let headers = new Headers();
        headers.append('Content-Type', 'application/json');
        let url = this.url + providerTypeId;
        console.log('delete provider by id: ' + providerTypeId);
        console.log("sending http DELETE to " +url);
        return this._http.delete(url, {headers: headers})
            .toPromise()
            .catch((err)=>console.error(err));
    }

    editAndSaveType(type:ProviderType) {
        if (type) {
            console.log('updating provider type with id: ' + type);
            this.putType(type);
        }
    }

    putType(type:ProviderType) {
        let headers = new Headers();
        headers.append('Content-Type', 'application/json');
        console.log("sending http PUT to " +this.url + type);
        console.log("json obj: " + JSON.stringify(type));
        return this._http.put(this.url + type, JSON.stringify(type), {headers: headers})
            .toPromise()
            .then(()=>type)
            .catch((err)=>console.error(err));
    }

}