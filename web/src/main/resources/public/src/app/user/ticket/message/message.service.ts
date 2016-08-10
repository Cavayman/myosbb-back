import {Injectable} from "@angular/core";
import {Http, Headers} from "@angular/http";
import "rxjs/add/operator/map";
import "rxjs/add/operator/toPromise";
import {Ticket, ITicket} from '..//..//ticket/ticket';
import {IMessage} from './message';

@Injectable()
export class MessageService { 

 private getUrl:string = 'http://localhost:52430/restful/message/ticket';

    constructor(private http: Http) { }

    getAllMessages(ticket:ITicket): Promise<IMessage[]> {
        let url = `${this.getUrl}/${ticket.ticketId}`;
        return this.http.get(url)
                 .toPromise()
                 .then(res => res.json())
                 .catch(this.handleError);
                }

 private handleError(error: any):Promise<any> {
        console.log('HandleError', error);
        return Promise.reject(error.message || error);
    }
}