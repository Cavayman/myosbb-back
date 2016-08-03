import {Injectable} from "@angular/core";
import {Headers, Http} from "@angular/http";
import {Observable} from "rxjs/Observable";
import "rxjs/add/operator/map";
import {Report} from "./report.interface";
import "rxjs/add/operator/toPromise";


@Injectable()
export class ReportService {

    private getReportUr = '/restful/report?pageNumber=';
    private delReportUrl = '/restful/report/';
    private updateReportUrl = '/restful/report/';

    constructor(private _http:Http) {
    }

    getAllReports(pageNumber:number):Observable<any> {
        return this._http.get(this.getReportUr + pageNumber)
            .map((response)=> response.json())
            .catch((error)=>Observable.throw(error));
    }

    getAllReportsSorted(pageNumber:number, name:string, order:boolean):Observable<any> {
        return this._http.get(this.getReportUr + pageNumber + '&&sortedBy=' + name + '&&asc=' + order)
            .map((response)=> response.json())
            .catch((error)=>Observable.throw(error));
    }

    deleteReportById(reportId:number) {
        let headers = new Headers();
        headers.append('Content-Type', 'application/json');
        let url = this.delReportUrl + reportId;
        console.log('delete report by id: ' + reportId);
        return this._http.delete(url, {headers: headers})
            .toPromise()
            .catch((error)=>console.error(error));

    }

    editAndSave(report:Report) {
        if (report.reportId) {
            console.log('updating report with id: ' + report.reportId);
            this.put(report);
        }
    }

    put(report:Report) {
        let headers = new Headers();
        headers.append('Content-Type', 'application/json');
        return this._http.put(this.updateReportUrl, JSON.stringify(report), {headers: headers})
            .toPromise()
            .then(()=>report)
            .catch((error)=>console.error(error));
    }


}