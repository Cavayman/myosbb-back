import {Http, Headers,RequestOptions} from '@angular/http';
import {Injectable} from "@angular/core";
import {Observable} from 'rxjs/observable';
import 'rxjs/add/operator/map';
import ApiService = require("../../../shared/services/api.service");

@Injectable ()
export class apartmentProfileService{

    constructor (private http:Http){}

    getCurrentApartment(id:number):Observable<any>{
        let url = ApiService.serverUrl + '/restful/apartment/'+id;
        return this.http.get(url)
            .map(response=>response.json());
         
    }
    
    getUsersInApartment(id:number):Observable<any>{
        let url=ApiService.serverUrl+'/restful/apartment/users'+id;
        return this.http.get(url)
            .map(res=>res.json());
        
    }

    getOwnerInApartment(id:number):Observable<any>{
        let url=ApiService.serverUrl+'/restful/apartment/owner'+id;
        return this.http.get(url)
            .map(res=>res.json());
        
    }
    

}
