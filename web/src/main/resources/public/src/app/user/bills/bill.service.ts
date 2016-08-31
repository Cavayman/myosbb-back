import {Injectable} from "@angular/core";
import {Headers, Http} from "@angular/http";
import {Observable} from "rxjs/Observable";
import "rxjs/add/operator/map";
import "rxjs/add/operator/toPromise";
import {SearchDTO} from "../../../shared/models/search.model";
import {BillDTO} from "./show_bill_dto.interface";
import ApiService = require("../../../shared/services/api.service");

@Injectable()
export class BillService {

    private userBillsURL: string = ApiService.serverUrl + '/restful/bill/user/';
    private billsURL: string = ApiService.serverUrl + '/restful/bill';
    private headers = new Headers({'Authorization': 'Bearer ' + localStorage.getItem('token')});

    constructor(private _http: Http) {
        this.headers.append('Content-Type', 'application/json');
    }


    getAllByRole(osbbRole: string, userId: number, searchDTO: SearchDTO, status: string): Observable<any> {
        if (osbbRole != 'HEAD') {
            console.log('get all user bills');
            return this.getAllByUser(userId, searchDTO, status);
        } else {
            console.log('get osbb bills');
            return this._http.post(this.billsURL + '/?status=' + status, JSON.stringify(searchDTO))
                .map((response)=> response.json())
                .catch((error)=>Observable.throw(error));
        }

    }

    getAllByUser(userId: number, searchDTO: SearchDTO, status: string): Observable<any> {
        return this._http.post(this.userBillsURL + userId + '/all?status=' + status, JSON.stringify(searchDTO))
            .map((response)=> response.json())
            .catch((error)=>Observable.throw(error));
    }

    save(bill: BillDTO): Observable<any> {
        if (bill.billId) {
            return this.update(bill);
        }
        return this.saveBill(bill);

    }

    saveBill(bill: BillDTO): Observable<any> {
        return this._http.post(this.billsURL + '/save', JSON.stringify(bill))
            .catch((error)=>Observable.throw(error));
    }

    update(bill: BillDTO): Observable<any> {
        return this._http.put(this.billsURL, JSON.stringify(bill))
            .catch((error)=>Observable.throw(error));
    }


    deleteById(billId: number): Observable<any> {
        return this._http.delete(this.billsURL + '/' + billId)
            .catch((error)=>Observable.throw(error))
    }


}