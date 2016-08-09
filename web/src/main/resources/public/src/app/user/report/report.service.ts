import {Injectable} from "@angular/core";
import {Headers, Http} from "@angular/http";
import {Observable} from "rxjs/Observable";
import "rxjs/add/operator/map";
import {Report} from "./report.interface";
import "rxjs/add/operator/toPromise";
import ApiService = require("../../../shared/services/api.service");


@Injectable()
export class ReportService {

    private getReportUr = ApiService.serverUrl + '/restful/report?pageNumber=';
    private delReportUrl = ApiService.serverUrl + '/restful/report/';
    private updateReportUrl = ApiService.serverUrl + '/restful/report/';

    private getReportByPageNumberUr = 'http://localhost:52430/restful/report?pageNumber=';
    private delReportUrl = 'http://localhost:52430/restful/report/';
    private updateReportUrl = 'http://localhost:52430/restful/report/';
    private getReportByParamURL = 'http://localhost:52430/restful/report/between?';


    constructor(private _http: Http) {
    }

    getAllReports(pageNumber: number): Observable<any> {
        return this._http.get(this.getReportByPageNumberUr + pageNumber)
            .map((response)=> response.json())
            .catch((error)=>Observable.throw(error));
    }

    getAllReportsSorted(pageNumber: number, name: string, order: boolean): Observable<any> {
        return this._http.get(this.getReportByPageNumberUr + pageNumber + '&&sortedBy=' + name + '&&order=' + order)
            .map((response)=> response.json())
            .catch((error)=>Observable.throw(error));
    }

    searchByDates(dateFrom: string, dateTo: string): Observable<any> {
        return this._http.get(this.getReportByParamURL
            + 'dateFrom=' + dateFrom + '&&'
            + 'dateTo=' + dateTo).map((response)=>response.json())
            .catch((error)=>Observable.throw(error));
    }

    deleteReportById(reportId: number) {
        let headers = new Headers();
        headers.append('Content-Type', 'application/json');
        let url = this.delReportUrl + reportId;
        console.log('delete report by id: ' + reportId);
        return this._http.delete(url, {headers: headers})
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
        let headers = new Headers();
        headers.append('Content-Type', 'application/json');
        return this._http.put(this.updateReportUrl, JSON.stringify(report), {headers: headers})
            .toPromise()
            .then(()=>report)
            .catch((error)=>console.error(error));
    }


}