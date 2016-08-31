import {Injectable} from "@angular/core";
import {Headers, Http} from "@angular/http";
import {Observable} from "rxjs/Observable";
import "rxjs/add/operator/map";
import ApiService = require("../../../../shared/services/api.service");


@Injectable()
export class BillChartService {

    private billChartURL: string = ApiService.serverUrl + '/restful/chart';
    private headers = new Headers({'Authorization': 'Bearer ' + localStorage.getItem('token')});

    constructor(private _http: Http) {
        this.headers.append('Content-Type', 'application/json');
    }

    getPercentageChartData(): Observable<any> {
        return this._http.get(this.billChartURL+'/percentage', {headers: this.headers})
            .map((response)=> response.json())
            .catch((error)=>Observable.throw(error));
    }


}