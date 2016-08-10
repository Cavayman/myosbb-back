import {Http, Headers,RequestOptions} from '@angular/http';
import {Injectable} from "@angular/core";
import {Observable} from 'rxjs/observable';
import {ApartmentModel} from './Apartment.model';
import 'rxjs/add/operator/map';
import ApiService = require("../../../shared/services/api.service");

@Injectable ()
export class ApartmentService{
    
    constructor (private http:Http){}
    
    getAllApartments():Observable<any>{
        let headers = new Headers();
        headers.append('Content-Type', 'application/json');
        return this.http.get(ApiService.serverUrl + "/restful/apartment")
            .map(response => response.json());
            }


    addApartment (am:ApartmentModel):Promise<ApartmentModel>{
        let body =JSON.stringify(am);
        let headers = new Headers({'Content-Type': 'application/json'});
        let options = new RequestOptions({headers});
        console.log("add model...." + body);
        return this.http.post(ApiService.serverUrl + "/restful/apartment", body, options)
            .toPromise()
            .then(res=>res.json());
        
    }
    deleteApartment(am:ApartmentModel):Promise<ApartmentModel>{
        let headers = new Headers({'Content-Type': 'application/json'});
        let options = new RequestOptions({headers});
        let url = ApiService.serverUrl + '/restful/apartment/' + am.apartmentId;
        console.log("deleted item" + am);
       return  this.http.delete(url, options)
           .toPromise()
           .then(res =>am);

    }

    editAndSave(am:ApartmentModel) {
        if (am.apartmentId) {
            console.log("updating Apartment" + am.apartmentId);
            this.put(am);
        }
    }

        put(apartmentModel:ApartmentModel) {
            let headers = new Headers();
            headers.append('Content-Type', 'application/json');
            return this.http.put(ApiService.serverUrl + "/restful/apartment", JSON.stringify(apartmentModel), {headers: headers})
                .toPromise()
                .then(()=>apartmentModel)
                .catch((error)=>console.error(error));
        }



    }
