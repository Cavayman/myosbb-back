import {Injectable} from "@angular/core";
import {Headers, Http} from "@angular/http";
import {Observable} from "rxjs/Observable";
import "rxjs/add/operator/map";
import {Report} from "./report.interface";
import "rxjs/add/operator/toPromise";
import {SearchDTO} from "../../../shared/models/search.model";
import ApiService = require("../../../shared/services/api.service");

@Injectable()
export class BillService {

    private getAllBillsByUserUrl: string = ApiService.serverUrl + '/restful/bill/user/';
    private headers = new Headers({'Authorization': 'Bearer ' + localStorage.getItem('token')});

    constructor(private _http: Http) {
        this.headers.append('Content-Type', 'application/json');
    }

    getAllUserBills(userId: number, searchDTO: SearchDTO, status: string): Observable<any> {
        if (!searchDTO) {
            return;
        }
        return this._http.post(this.getAllBillsByUserUrl + userId + '/all?status=' + status, JSON.stringify(searchDTO), {headers: this.headers})
            .map((response)=> response.json())
            .catch((error)=>Observable.throw(error));
    }


    getAllUserBillsSorted(userId: number, pageNumber: number, name: string, order: boolean): Observable<any> {
        return this._http.get(this.getAllBillsByUserUrl + userId + '/all?pageNumber=' + pageNumber + '&&sortedBy=' + name + '&&order=' + order, {headers: this.headers})
            .map((response)=> response.json())
            .catch((error)=>Observable.throw(error));
    }
}