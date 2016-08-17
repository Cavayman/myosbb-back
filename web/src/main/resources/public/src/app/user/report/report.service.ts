import {Injectable} from "@angular/core";
import {Headers, Http} from "@angular/http";
import {Observable} from "rxjs/Observable";
import "rxjs/add/operator/map";
import {Report} from "./report.interface";
import "rxjs/add/operator/toPromise";
import ApiService = require("../../../shared/services/api.service");


@Injectable()
export class ReportService {

    private getReportByPageNumberUr = ApiService.serverUrl + '/restful/report?pageNumber=';
    private delReportUrl = ApiService.serverUrl + '/restful/report/';
    private updateReportUrl = ApiService.serverUrl + '/restful/report/';
    private getReportByParamURL = ApiService.serverUrl + '/restful/report/between?';
    private getReportBySearchParamURL = ApiService.serverUrl + '/restful/report/find?searchParam=';
    private headers = new Headers({'Authorization': 'Bearer ' + localStorage.getItem('token')});

    private getReportsByUserUrl = ApiService.serverUrl + '/restful/report/user/';

    constructor(private _http: Http) {
        this.headers.append('Content-Type', 'application/json');
    }


    //for current logged-in user
    getAllUserReports(userId: number, pageNumber: number, selectedRow: number): Observable<any> {
        return this._http.get(this.getReportsByUserUrl + userId + '/all?pageNumber=' + pageNumber + '&&rowNum=' + selectedRow
            , {headers: this.headers})
            .map((response)=> response.json())
            .catch((error)=>Observable.throw(error));
    }

    getAllUserReportsSorted(userId: number, pageNumber: number, name: string, order: boolean): Observable<any> {
        return this._http.get(this.getReportsByUserUrl + userId + '/all?pageNumber=' + pageNumber + '&&sortedBy=' + name + '&&order=' + order, {headers: this.headers})
            .map((response)=> response.json())
            .catch((error)=>Observable.throw(error));
    }

    searchUserReportsByInputParam(userId: number, value: string): Observable<any> {
        return this._http.get(this.getReportsByUserUrl + userId + "/find?searchParam=" + value, {headers: this.headers})
            .map((response)=>response.json())
            .catch((error)=>Observable.throw(error));
    }

    searchUserReportsByDates(userId: number, dateFrom: string, dateTo: string): Observable<any> {
        return this._http.get(this.getReportsByUserUrl + userId + "/between?"
            + 'dateFrom=' + dateFrom + '&&'
            + 'dateTo=' + dateTo, {headers: this.headers})
            .map((response)=>response.json())
            .catch((error)=>Observable.throw(error));
    }


    // for admin
    getAllReports(pageNumber: number, selectedRow: number): Observable<any> {
        return this._http.get(this.getReportByPageNumberUr + pageNumber + '&&rowNum=' + selectedRow, {headers: this.headers})
            .map((response)=> response.json())
            .catch((error)=>Observable.throw(error));
    }

    getAllReportsSorted(pageNumber: number, name: string, order: boolean): Observable<any> {
        return this._http.get(this.getReportByPageNumberUr + pageNumber + '&&sortedBy=' + name + '&&order=' + order, {headers: this.headers})
            .map((response)=> response.json())
            .catch((error)=>Observable.throw(error));
    }

    searchByDates(dateFrom: string, dateTo: string): Observable<any> {
        return this._http.get(this.getReportByParamURL
            + 'dateFrom=' + dateFrom + '&&'
            + 'dateTo=' + dateTo, {headers: this.headers}).map((response)=>response.json())
            .catch((error)=>Observable.throw(error));
    }

    searchByInputParam(value: string): Observable<any> {
        return this._http.get(this.getReportBySearchParamURL + value, {headers: this.headers})
            .map((response)=>response.json())
            .catch((error)=>Observable.throw(error));
    }

    deleteReportById(reportId: number) {
        let url = this.delReportUrl + reportId;
        console.log('delete report by id: ' + reportId);
        return this._http.delete(url, {headers: this.headers})
            .toPromise()
            .catch((error)=>console.error(error));

    }

    editAndSave(report: Report) {
        if (report.reportId) {
            console.log('updating report with id: ' + report.reportId);
            this.put(report);
        }
    }

    put(report: Report) {
        return this._http.put(this.updateReportUrl, JSON.stringify(report), {headers: this.headers})
            .toPromise()
            .then(()=>report)
            .catch((error)=>console.error(error));
    }


}