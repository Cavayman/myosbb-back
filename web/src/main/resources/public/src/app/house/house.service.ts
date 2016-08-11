import {Injectable} from "@angular/core";
import {Http} from "@angular/http";
import {Observable} from "rxjs/Observable";
import "rxjs/add/operator/map";
import ApiService = require("../../shared/services/api.service");


@Injectable()
export class HouseService {

    public housesByIdUrl: string = ApiService.serverUrl + '/restful/house/';
    public housesByPageUrl = ApiService.serverUrl + '/restful/house?pageNumber=';


    constructor(private _http: Http) {
    }

    getHouseById(houseId: number): Observable<any> {
        return this._http.get(this.housesByIdUrl + houseId)
            .map((response)=> response.json())
            .catch((error)=>Observable.throw(error));
    }


    getAllHousesByPageNumber(pageNumber: number): Observable<any> {
        return this._http.get(this.housesByPageUrl + pageNumber)
            .map((response)=> response.json())
            .catch((error)=>Observable.throw(error));

    }


}