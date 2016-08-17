import {Injectable} from "@angular/core";
import {Http, Headers} from "@angular/http";
import "rxjs/add/operator/map";
import "rxjs/add/operator/toPromise";
import {Option} from "./option";
import ApiService = require("../../../shared/services/api.service");

@Injectable()
export class OptionService { 

    private url:string = ApiService.serverUrl +'/restful/option';

    constructor(private http: Http) { }

    getAllOptions(): Promise<Option[]> {
        let headers = new Headers({'Authorization': 'Bearer ' + localStorage.getItem('token')});
        headers.append('Content-Type', 'application/json');
        return this.http.get(this.url, {headers})
                 .toPromise()
                 .then(res => res.json())
                 .catch(this.handleError);
    }

    addOption(option:Option, voteId: number): Promise<Option> {
        let headers = new Headers({'Authorization': 'Bearer ' + localStorage.getItem('token')});
        headers.append('Content-Type', 'application/json');
        let url = this.url +"/"+ voteId;
        return this.http.post(url, JSON.stringify(option), {headers})
                        .toPromise()
                        .then(res => res.json())
                        .catch(this.handleError);
    }

    toScoreOption(optionId: number, userId: number) {
         let headers = new Headers({'Authorization': 'Bearer ' + localStorage.getItem('token')});
         headers.append('Content-Type', 'application/json');
         let url = this.url +"/" + optionId + "," + userId;
         return this.http.get(url,{headers}).toPromise();
    }

    private handleError(error: any):Promise<any> {
        console.log('HandleError', error);
        return Promise.reject(error.message || error);
    }
   
}