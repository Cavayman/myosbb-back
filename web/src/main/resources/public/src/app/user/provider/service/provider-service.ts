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
    private headers = new Headers({'Authorization': 'Bearer ' + localStorage.getItem('token')});

    constructor(private _http:Http){
        this.headers.append('Content-Type', 'application/json');
    }

    getProviders(pageNumber:number) : Observable<any>{
        console.log("get providers inside service, pagenum" + pageNumber);
        console.log("sending http GET to " +this.urlWithParams + pageNumber);
        console.log("headers: ", this.headers);
        return  this._http.get(this.urlWithParams + pageNumber, {headers: this.headers})
            .map(res => res.json())
            .catch((err)=>Observable.throw(err));
    }
    getAllProviders() : Observable<any>{
        return  this._http.get(this.url,  {headers: this.headers})
            .map(res => res.json())
            .catch((err)=>Observable.throw(err));
    }
    getProvidersByState(pageNumber:number, onlyActive : boolean) : Observable<any>{
        console.log("get only active providers inside service, pagenum" + pageNumber);
        console.log("headers: ", this.headers);
        console.log("sending http GET to " +this.urlWithParams + pageNumber  + '&&actv=' + onlyActive);
        return  this._http.get(this.urlWithParams + pageNumber + '&&actv=' + onlyActive,
            {headers: this.headers})
            .map(res => res.json())
            .catch((err)=>Observable.throw(err));
    }

    getProviderById(id: number) : Observable<any> {
       // let options = new RequestOptions({headers: this.headers});
        console.log("ok");
        return this._http.get(this.url + id, {headers: this.headers})
            .map(res => res.json());
    }

    getSortedProviders(pageNumber:number, name:string, order:boolean):Observable<any> {
        return this._http.get(this.urlWithParams + pageNumber + '&&sortedBy=' + name + '&&asc=' + order,
            {headers: this.headers})
            .map((res)=> res.json())
            .catch((err)=>Observable.throw(err));
    }
    getSortedActiveProviders(pageNumber:number, name:string, order:boolean, onlyActive:boolean):Observable<any> {
        return this._http.get(this.urlWithParams + pageNumber + '&&sortedBy=' + name + '&&asc=' + order + '&&actv=' + onlyActive,
            {headers: this.headers})
            .map((res)=> res.json())
            .catch((err)=>Observable.throw(err));
    }
    deleteProviderById(providerId:number) {
        let url = this.url + providerId;
        console.log('delete provider by id: ' + providerId);
        console.log("sending http DELETE to " +url);
        console.log("headers: ", this.headers);
        return this._http.delete(url, {headers: this.headers})
            .toPromise()
            .catch((err)=>console.error(err));
    }

    editAndSave(provider:Provider) {
        if (provider.providerId) {
            console.log('updating provider with id: ' + provider.providerId);
            this.put(provider);
        } else this.addProvider(provider);
    }

    addProvider(provider:Provider){
        console.log("sending http POST to " +this.url);
        console.log("saving ", provider);
        console.log("headers: ", this.headers);
        return this._http.post(this.url, JSON.stringify(provider), {headers: this.headers})
            .toPromise()
            .then(()=>provider)
            .catch((err)=>console.error(err));
    }
    put(provider:Provider) {
        console.log("sending http PUT to " +this.url + provider.providerId);
        console.log("json obj: " + JSON.stringify(provider));
        console.log("headers: ", this.headers);
        return this._http.put(this.url + provider.providerId, JSON.stringify(provider),
            {headers: this.headers})
            .toPromise()
            .then(()=>provider)
            .catch((err)=>console.error(err));
    }

    findProviderByNameOrDescription(search: string) :  Observable<any>{
        console.log("searching providers");
        console.log("param is" + search);
        console.log("headers: ", this.headers);
        return  this._http.get(this.url + "find?name="+search, {headers: this.headers})
            .map(res => res.json())
            .catch((err)=>Observable.throw(err));
    }
}