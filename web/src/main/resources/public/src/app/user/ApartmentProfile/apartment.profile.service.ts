import {Http, Headers,RequestOptions} from '@angular/http';
import {Injectable} from "@angular/core";
import {Observable} from 'rxjs/observable';
import {singleApartmentModel} from './Apartment.model';
import 'rxjs/add/operator/map';
@Injectable ()
export class apartmentProfileService{

    constructor (private http:Http){}

    getCurrentApartment(id:number):Observable<any>{
        let headers = new Headers({'Content-Type': 'application/json'});
        let options = new RequestOptions({headers});
        let url='http://localhost:52430/restful/apartment/'+id;
        return this.http.get(url)
            .map(response=>response.json());
           // .toPromise()
           // .then(response=>response.json());
    }
    

}
