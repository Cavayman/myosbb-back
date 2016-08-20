import {Injectable} from "@angular/core";
import {Headers, Http} from "@angular/http";
import {Observable} from "rxjs/Observable";
import "rxjs/add/operator/map";
import {Report} from "./report.interface";
import "rxjs/add/operator/toPromise";
import ApiService = require("../../../shared/services/api.service");

@Injectable()
export class BillService {

    private getAllBillsByUserUrl: string = ApiService.serverUrl + '/restful/bill/user/';
    private headers = new Headers({'Authorization': 'Bearer ' + localStorage.getItem('token')});

    constructor(private _http: Http) {
        this.headers.append('Content-Type', 'application/json');
    }

    getAllUserBills(userId: number, pageNumber: number, selectedRow: number): Observable<any> {
        return this._http.get(this.getAllBillsByUserUrl + userId + '/all?pageNumber=' + pageNumber + '&&rowNum=' + selectedRow, {headers: this.headers})
            .map((response)=> response.json())
            .catch((error)=>Observable.throw(error));
    }


    getAllUserBillsSorted(userId: number, pageNumber: number, name: string, order: boolean): Observable<any> {
        return this._http.get(this.getAllBillsByUserUrl + userId + '/all?pageNumber=' + pageNumber + '&&sortedBy=' + name + '&&order=' + order, {headers: this.headers})
            .map((response)=> response.json())
            .catch((error)=>Observable.throw(error));
    }
}