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
    private url = ApiService.serverUrl + '/restful/providertype/';
    private headers = new Headers({'Authorization': 'Bearer ' + localStorage.getItem('token')});

    constructor(private _http:Http){
        this.headers.append('Content-Type', 'application/json');
    }

    getProviderTypes() : Observable<any>{
        console.log("get all provider types inside service");
        console.log("sending http GET");
        console.log("headers: ", this.headers);
        return  this._http.get(this.url, {headers: this.headers})
            .map(res => res.json())
            .catch((err)=>Observable.throw(err));
    }

    getProviderTypeById(id: number) :  Observable<any> {
        let options = new RequestOptions({headers: this.headers});
        return this._http.get(this.url + id, {headers: this.headers})
            .map(r => r.json());
    }


    deleteProviderTypeById(providerTypeId:number) {
        let url = this.url + providerTypeId;
        console.log('delete provider by id: ' + providerTypeId);
        console.log("sending http DELETE to " +url);
        console.log("headers: ", this.headers);
        return this._http.delete(url, {headers: this.headers})
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
        console.log("sending http PUT to " +this.url + type);
        console.log("json obj: " + JSON.stringify(type));
        console.log("headers: ", this.headers);
        return this._http.put(this.url + type, JSON.stringify(type),
            {headers: this.headers})
            .toPromise()
            .then(()=>type)
            .catch((err)=>console.error(err));
    }

}