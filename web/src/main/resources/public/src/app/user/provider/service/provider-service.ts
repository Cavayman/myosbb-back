/**
 * Created by Anastasiia Fedorak  8/2/16.
 */
import {Injectable} from "@angular/core";
import {Http, Headers, Response, RequestOptions} from "@angular/http";
import {Observable} from "rxjs/Rx";
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/toPromise';
import ApiService = require("../../../../shared/services/api.service");
import {Provider} from "../../../../shared/models/provider.interface";

@Injectable()
export class ProviderService {
    private url = ApiService.serverUrl + '/restful/provider/';
    private urlWithParams = ApiService.serverUrl + '/restful/provider?pageNum=';

    constructor(private _http:Http){
    }

    getProviders(pageNumber:number) : Observable<any>{
        console.log("get providers inside service, pagenum" + pageNumber);
        console.log("sending http GET to " +this.urlWithParams + pageNumber);
        return  this._http.get(this.urlWithParams + pageNumber)
            .map(res => res.json())
            .catch((err)=>Observable.throw(err));
    }
    getAllProviders() : Observable<any>{
        return  this._http.get(this.url)
            .map(res => res.json())
            .catch((err)=>Observable.throw(err));
    }
    getProvidersByState(pageNumber:number, onlyActive : boolean) : Observable<any>{
        console.log("get only active providers inside service, pagenum" + pageNumber);
        console.log("sending http GET to " +this.urlWithParams + pageNumber  + '&&actv=' + onlyActive);
        return  this._http.get(this.urlWithParams + pageNumber + '&&actv=' + onlyActive)
            .map(res => res.json())
            .catch((err)=>Observable.throw(err));
    }

    getProviderById(id: number) : Observable<any> {
        console.log("ok");
        return this._http.get(this.url + id)
            .map(res => res.json())
            .catch((err)=>Observable.throw(err));
    }

    getSortedProviders(pageNumber:number, name:string, order:boolean):Observable<any> {
        return this._http.get(this.urlWithParams + pageNumber + '&&sortedBy=' + name + '&&asc=' + order)
            .map((res)=> res.json())
            .catch((err)=>Observable.throw(err));
    }
    getSortedActiveProviders(pageNumber:number, name:string, order:boolean, onlyActive:boolean):Observable<any> {
        return this._http.get(this.urlWithParams + pageNumber + '&&sortedBy=' +
            name + '&&asc=' + order + '&&actv=' + onlyActive)
            .map((res)=> res.json())
            .catch((err)=>Observable.throw(err));
    }
    deleteProviderById(providerId:number) {
        console.log('delete provider by id: ' + providerId);
        console.log("sending http DELETE to " +this.url + providerId);
        return this._http.delete(this.url + providerId)
            .map(res => res.json())
            .catch((err)=>Observable.throw(err));
    }


    addProvider(provider:Provider){
        console.log("sending http POST to " +this.url);
        console.log("saving ", provider);
        return this._http.post(this.url, JSON.stringify(provider))
            .map(res => res.json())
            .catch((err)=>Observable.throw(err));
    }
    editProvider(provider:Provider) {
        console.log('updating provider with id: ' + provider.providerId);
        console.log("sending http PUT to " +this.url + provider.providerId);
        console.log("json obj: " + JSON.stringify(provider));
        return this._http.put(this.url + provider.providerId, JSON.stringify(provider))
            .map(res => res.json())
            .catch((err)=>Observable.throw(err));
    }

    findProviderByNameOrDescription(search: string) :  Observable<any>{
        console.log("searching providers");
        console.log("param is" + search);
        return  this._http.get(this.url + "find?name="+search)
            .map(res => res.json())
            .catch((err)=>Observable.throw(err));
    }
}