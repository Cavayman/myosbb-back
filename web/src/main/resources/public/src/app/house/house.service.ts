import {Injectable} from "@angular/core";
import {Http} from "@angular/http";
import {Observable} from "rxjs/Observable";
import "rxjs/add/operator/map";


@Injectable()
export class HouseService {


    public getHouseByIdUrl: string = ApiService.serverUrl + '/restful/house/';
    public housesByIdUrl: string = 'http://localhost:52430/restful/house/';
    public housesByPageUrl = 'http://localhost:52430/restful/house?pageNumber=';


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