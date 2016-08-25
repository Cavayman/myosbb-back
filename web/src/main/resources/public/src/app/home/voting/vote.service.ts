import {Injectable} from "@angular/core";
import {Http, Headers} from "@angular/http";

import "rxjs/add/operator/map";
import "rxjs/add/operator/toPromise";

import {Vote} from "./vote";
import ApiService = require("../../../shared/services/api.service");


@Injectable()
export class VoteService { 

    private url:string = ApiService.serverUrl +'/restful/vote';
    private headers = new Headers({'Authorization': 'Bearer ' + localStorage.getItem('token')});

    constructor(private http: Http) {
        this. headers.append('Content-Type', 'application/json');
     }

    getAllVotes(): Promise<Vote[]> {
        return this.http.get(this.url , {headers: this.headers})
                 .toPromise()
                 .then(res => res.json())
                 .catch(this.handleError);
    }

    addVote(vote:Vote): Promise<Vote> {
        return this.http.post(this.url, JSON.stringify(vote), {headers: this.headers})
                        .toPromise()
                        .then(res => res.json())
                        .catch(this.handleError);
    }

    private handleError(error: any):Promise<any> {
        console.log('HandleError', error);
        return Promise.reject(error.message || error);
    }
}