import {Http, Headers,RequestOptions} from '@angular/http';
import {Injectable} from "@angular/core";
import {Observable} from 'rxjs/Observable';
import {ApartmentModel} from './Apartment.model';
import 'rxjs/add/operator/map';
import ApiService = require("../../../shared/services/api.service");

@Injectable ()
export class ApartmentService{
    
    constructor (private http:Http){}
    
    getAllApartments(pageNumber:number):Observable<any>{
        let headers = new Headers({'Authorization': 'Bearer '+localStorage.getItem('token')});
        headers.append('Content-Type', 'application/json');
        return this.http.get(ApiService.serverUrl + "/restful/apartment?pageNumber="+pageNumber)
            .map(response => response.json());
            }

    getAllApartments(pageNumber:number):Observable<any>{
        let headers = new Headers({'Authorization': 'Bearer '+localStorage.getItem('token')});
        headers.append('Content-Type', 'application/json');
        return this.http.get(ApiService.serverUrl + "/restful/apartment?pageNumber="+pageNumber)
            .map(response => response.json());
    }

    getSortedApartments(pageNumber:number, name:string, order:boolean):Observable<any> {
        let headers = new Headers({'Authorization': 'Bearer ' + localStorage.getItem('token')});
        headers.append('Content-Type', 'application/json');

        return this.http.get(ApiService.serverUrl + "/restful/apartment?pageNumber=" + pageNumber + '&&sortedBy=' + name + '&&asc=' + order)
            .map((res)=> res.json())
            .catch((err)=>Observable.throw(err));
    }

    getSortedApartmentsWithNumber(pageNumber:number, name:string, order:boolean,number:number):Observable<any> {
        let headers = new Headers({'Authorization': 'Bearer ' + localStorage.getItem('token')});
        headers.append('Content-Type', 'application/json');

        return this.http.get(ApiService.serverUrl + "/restful/apartment?pageNumber=" + pageNumber + '&&sortedBy=' + name + '&&asc=' + order+'&&number='+number)
            .map((res)=> res.json())
            .catch((err)=>Observable.throw(err));
    }

    addApartment (am:ApartmentModel):Promise<ApartmentModel>{
        let body =JSON.stringify(am);
        let headers = new Headers({'Authorization': 'Bearer '+localStorage.getItem('token')});
        headers.append('Content-Type', 'application/json');
        console.log("add model...." + body);
        return this.http.post(ApiService.serverUrl + "/restful/apartment", body )
            .toPromise()
            .then(res=>res.json());
        
    }
    deleteApartment(am:ApartmentModel):Promise<ApartmentModel>{
        let headers = new Headers({'Authorization': 'Bearer '+localStorage.getItem('token')});
        headers.append('Content-Type', 'application/json');
        let url = ApiService.serverUrl + '/restful/apartment/' + am.apartmentId;
        console.log("deleted item" + am);
       return  this.http.delete(url)
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
            let headers = new Headers({'Authorization': 'Bearer '+localStorage.getItem('token')});
            headers.append('Content-Type', 'application/json');
            return this.http.put(ApiService.serverUrl + "/restful/apartment", JSON.stringify(apartmentModel))
                .toPromise()
                .then(()=>apartmentModel)
                .catch((error)=>console.error(error));
        }


    getAllUsersInAppartment(id:number){
        let headers = new Headers({'Authorization': 'Bearer '+localStorage.getItem('token')});
        headers.append('Content-Type', 'application/json');
        return this.http.get(ApiService.serverUrl + "/restful/apartment/users"+id)
            .map(response => response.json());
    }
        
    



    }
