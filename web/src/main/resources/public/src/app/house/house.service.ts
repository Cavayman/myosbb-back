import {Injectable} from "@angular/core";
import {Http} from "@angular/http";
import {Observable} from "rxjs/Observable";
import "rxjs/add/operator/map";
import {HousePageObject} from "./house.page.object";
import ApiService = require("../../shared/services/api.service");


@Injectable()
export class HouseService {

    public houseURL: string = ApiService.serverUrl + '/restful/house/';
    public housesByPageUrl = ApiService.serverUrl + '/restful/house?pageNumber=';
    public housesBySearchParam = ApiService.serverUrl + '/restful/house/find?searchParam=';

    constructor(private _http: Http) {
    }

    getHouseById(houseId: number): Observable<any> {
        return this._http.get(this.houseURL + houseId)
            .map((response)=> response.json())
            .catch((error)=>Observable.throw(error));
    }

    deleteHouseById(houseId: number): Observable<any> {
        return this._http.delete(this.houseURL + houseId)
            .catch((error)=>Observable.throw(error));

    }


    listAllHouses(): Observable<any> {
        return this._http.get(this.houseURL + 'all')
            .map((response)=>response.json())
            .catch((error)=>Observable.throw(error));
    }

    getAllHousesByPageNumber(pageNumber: number, selectedRow: number): Observable<any> {
        return this._http.get(this.housesByPageUrl + pageNumber + '&&rowNum=' + selectedRow)
            .map((response)=> response.json())
            .catch((error)=>Observable.throw(error));

    }


    getAllApartmentsByHouseId(houseId: number): Observable<any> {
        return this._http.get(this.houseURL + houseId + '/apartments')
            .map((response)=> response.json())
            .catch((error)=>Observable.throw(error));
    }


    searchByInputParam(value: string): Observable<any> {
        console.log('service ', value);
        return this._http.get(this.housesBySearchParam + value)
            .map((response)=> response.json())
            .catch((error)=>Observable.throw(error));
    }

    saveHouse(house: HousePageObject): Observable<any> {
        return this._http.post(this.houseURL, JSON.stringify(house))
            .catch((error)=> Observable.throw(error));
    }


}