import {Injectable} from "@angular/core";
import {Http, Headers} from "@angular/http";
import "rxjs/add/operator/map";
import "rxjs/add/operator/toPromise";
import {Vote} from "./vote";
import ApiService = require("../../../shared/services/api.service");


@Injectable()
export class VoteService { 
    private url:string = ApiService.serverUrl +'/restful/vote';

    constructor(private http: Http) { }

    getAllVotes(): Promise<Vote[]> {
        let headers = new Headers({'Authorization': 'Bearer ' + localStorage.getItem('token')});
        headers.append('Content-Type', 'application/json');
        return this.http.get(this.url , {headers})
                 .toPromise()
                 .then(res => res.json())
                 .catch(this.handleError);
    }

    addVote(vote:Vote, userId:number): Promise<Vote> {
        let url = this.url + '/' + userId;
        //let headers = new Headers({'Content-Type': 'application/json' });
        let headers = new Headers({'Authorization': 'Bearer ' + localStorage.getItem('token')});
        headers.append('Content-Type', 'application/json');
        return this.http.post(url, JSON.stringify(vote), {headers})
                        .toPromise()
                        .then(res => res.json())
                        .catch(this.handleError);
    }

    private handleError(error: any):Promise<any> {
        console.log('HandleError', error);
        return Promise.reject(error.message || error);
    }
   
}