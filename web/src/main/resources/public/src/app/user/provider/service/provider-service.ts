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

    getProviderById(id: number) : Observable<any> {
        let headers = new Headers({'Content-Type': 'application/json'});
        let options = new RequestOptions({headers});
        console.log("ok");
        return this._http.get(this.url + id)
            .map(res => res.json());
    }

    getSortedProviders(pageNumber:number, name:string, order:boolean):Observable<any> {
        return this._http.get(this.urlWithParams + pageNumber + '&&sortedBy=' + name + '&&asc=' + order)
            .map((res)=> res.json())
            .catch((err)=>Observable.throw(err));
    }

    deleteProviderById(providerId:number) {
        let headers = new Headers();
        headers.append('Content-Type', 'application/json');
        let url = this.url + providerId;
        console.log('delete provider by id: ' + providerId);
        console.log("sending http DELETE to " +url);
        return this._http.delete(url, {headers: headers})
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
        let headers = new Headers();
        headers.append('Content-Type', 'application/json');
        console.log("sending http POST to " +this.url);
        console.log("saving ", provider);
        return this._http.post(this.url, JSON.stringify(provider), {headers: headers})
            .toPromise()
            .then(()=>provider)
            .catch((err)=>console.error(err));
    }
    put(provider:Provider) {
        let headers = new Headers();
        headers.append('Content-Type', 'application/json');
        console.log("sending http PUT to " +this.url + provider.providerId);
        console.log("json obj: " + JSON.stringify(provider));
        return this._http.put(this.url + provider.providerId, JSON.stringify(provider), {headers: headers})
            .toPromise()
            .then(()=>provider)
            .catch((err)=>console.error(err));
    }

    findProviderByNameOrDescription(search: string) :  Observable<any>{
        console.log("searching providers");
        console.log("param is" + search);
        return  this._http.get(this.url + "find?name="+search)
            .map(res => res.json())
            .catch((err)=>Observable.throw(err));
    }
}