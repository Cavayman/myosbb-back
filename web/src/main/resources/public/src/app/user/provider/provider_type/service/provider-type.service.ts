/**
 * Created by Anastasiia Fedorak  8/4/16.
 */
import {Injectable} from "@angular/core";
import {Http, Response, Headers} from "@angular/http";
import {Observable} from "rxjs/Rx";
import {ProviderType} from "../../../../../shared/models/provider.type.interface";


@Injectable()
export class ProviderTypeService {
   private url = '/restful/providertype/';
   //  private url = 'http://localhost:52430/restful/providertype/';

    constructor(private _http:Http){
    }


    // getProviderTypes(): Promise<ProviderType[]> {
    //     return this._http.get(this.url)
    //         .toPromise()
    //         .then(res => res.json())
    // }

    getProviderTypes() : Observable<any>{
        return  this._http.get(this.url)
            .map(res => res.json())
            .catch((err)=>Observable.throw(err));
    }

    getProviderTypeById(id: number) :  Observable<any> {
        console.log("ok");
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