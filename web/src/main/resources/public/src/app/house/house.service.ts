import {Injectable} from "@angular/core";
import {Http, Headers} from "@angular/http";
import {Observable} from "rxjs/Observable";
import "rxjs/add/operator/map";
import ApiService = require("../../shared/services/api.service");


@Injectable()
export class HouseService {

    public housesByIdUrl: string = ApiService.serverUrl + '/restful/house/';
    public housesByPageUrl = ApiService.serverUrl + '/restful/house?pageNumber=';
    public housesBySearchParam = ApiService.serverUrl + '/restful/house/find?searchParam=';
    private headers = new Headers({'Authorization': 'Bearer ' + localStorage.getItem('token')});


    constructor(private _http: Http) {
        this.headers.append('Content-Type', 'application/json');
    }

    getHouseById(houseId: number): Observable<any> {
        return this._http.get(this.housesByIdUrl + houseId, {headers: this.headers})
            .map((response)=> response.json())
            .catch((error)=>Observable.throw(error));
    }


    getAllHousesByPageNumber(pageNumber: number, selectedRow: number): Observable<any> {
        return this._http.get(this.housesByPageUrl + pageNumber + '&&rowNum=' + selectedRow, {headers: this.headers})
            .map((response)=> response.json())
            .catch((error)=>Observable.throw(error));

    }


    searchByInputParam(value: string): Observable<any> {
        console.log('service ', value);
        return this._http.get(this.housesBySearchParam + value, {headers: this.headers})
            .map((response)=> response.json())
            .catch((error)=>Observable.throw(error));
    }


}