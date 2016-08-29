import {Http, Headers} from "@angular/http";
import {Injectable} from "@angular/core";
import ApiService = require("../../../shared/services/api.service");

@Injectable()
export class CalendarService {

    private url = ApiService.serverUrl + '/restful/event/';

    constructor(private http: Http) {}

    getEvents() {
        let headers2 = new Headers({'Authorization': 'Bearer '+localStorage.getItem('token')});
        headers2.append('Content-Type', 'application/json');
        return this.http.get(this.url, {headers:headers2})
            .toPromise()
            .then(res => <any[]> res.json())
            .then(data => { console.log("service : " + data); return data; });
    }
}