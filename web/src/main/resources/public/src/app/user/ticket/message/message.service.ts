import {Injectable} from "@angular/core";
import {Http, Headers} from "@angular/http";
import "rxjs/add/operator/map";
import "rxjs/add/operator/toPromise";
import {Ticket, ITicket} from '../../ticket/ticket';
import {IMessage} from './message';
import ApiService = require("../../../../shared/services/api.service");


@Injectable()
export class MessageService { 
    private addMessageUrl:string = ApiService.serverUrl + '/restful/message/ticket';
private showUrl:string = ApiService.serverUrl + '/restful/message/';
private getOneUrl:string = ApiService.serverUrl + '/restful/ticket/';
 private getUrl:string = ApiService.serverUrl +'/restful/message/comments';

    constructor(private http: Http) { }

    
 private handleError(error: any):Promise<any> {
        console.log('HandleError', error);
        return Promise.reject(error.message || error);
    }

    getAllMessages(ticket:number): Promise<IMessage[]> {
    let headers = new Headers({'Authorization': 'Bearer '+localStorage.getItem('token')});
        headers.append('Content-Type', 'application/json');
        let url = `${this.showUrl}/${ticket}`;
        return this.http.get(url,{headers:headers})
                 .toPromise()
                 .then(res => res.json())
                 .catch(this.handleError);
                }

addMessage(message:IMessage): Promise<IMessage> {
       let headers = new Headers({'Authorization': 'Bearer '+localStorage.getItem('token')});
        headers.append('Content-Type', 'application/json');
         let url = `${this.addMessageUrl}/${message.idTicket}`;
        return this.http.post(url, JSON.stringify(message), {headers:headers})
                        .toPromise()
                        .then(res => res.json())
                        .catch(this.handleError);
    }


     getTicketbyId(ticketId:number):Promise<ITicket[]> {
        console.log("service Get one ticket with id "+ ticketId);        
        let headers = new Headers({'Authorization': 'Bearer '+localStorage.getItem('token')});
        headers.append('Content-Type', 'application/json');
        let url = `${this.getOneUrl}/${ticketId}`;
        return this.http.get(url,{headers:headers})
                 .toPromise()
                 .then(res => res.json())
                 .catch(this.handleError);
                }          
}