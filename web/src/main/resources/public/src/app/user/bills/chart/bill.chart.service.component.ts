import {Injectable} from "@angular/core";
import {Http} from "@angular/http";
import {Observable} from "rxjs/Observable";
import "rxjs/add/operator/map";
import ApiService = require("../../../../shared/services/api.service");


@Injectable()
export class BillChartService {

    private billChartURL: string = ApiService.serverUrl + '/restful/chart';

    constructor(private _http: Http) {
    }

    getPercentageChartData(): Observable<any> {
        return this._http.get(this.billChartURL + '/percentage')
            .map((response)=> response.json())
            .catch((error)=>Observable.throw(error));
    }

    getBarChartData(year: number): Observable<any> {
        return this._http.get(this.billChartURL +'/'+ year + '/bar')
            .map((response)=>response.json())
            .catch((error)=>Observable.throw(error));
    }


}