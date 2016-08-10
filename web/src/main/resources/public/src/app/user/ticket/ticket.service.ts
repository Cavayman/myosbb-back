import {Injectable} from "@angular/core";
import {Http, Headers} from "@angular/http";
import "rxjs/add/operator/map";
import "rxjs/add/operator/toPromise";

import {ITicket} from './ticket';
import {IMessage} from './message/message';
@Injectable()
export class TicketService { 

    private deleteUrl:string = 'http://localhost:52430/restful/ticket';
    private postUrl:string = 'http://localhost:52430/restful/ticket';
    private putUrl:string = 'http://localhost:52430/restful/ticket/';                              
    private getUrl:string = 'http://localhost:52430/restful/ticket/';
 private showUrl:string = 'http://localhost:52430/restful/message/comments';
private addMessageUrl:string = 'http://localhost:52430/restful/message/ticket/';
    constructor(private http: Http) { }

    getAllTicket(): Promise<ITicket[]> {
        return this.http.get(this.getUrl)
                 .toPromise()
                 .then(res => res.json())
                 .catch(this.handleError);
                }

    addTicket(ticket:ITicket): Promise<ITicket> {
        let headers = new Headers({'Content-Type': 'application/json' });
        return this.http.post(this.postUrl, JSON.stringify(ticket), {headers})
                        .toPromise()
                        .then(res => res.json())
                        .catch(this.handleError);
    }

    editTicket(ticket:ITicket):Promise<ITicket>  {
        ticket.time= null;
        console.log("TicketService.editTicket(ticket) ===> [id:" + ticket.ticketId + "  name:" + ticket.name + 
        "   description:" + ticket.description + " state:"+ ticket.state +  "  time:"+ticket.time+"]");
        let headers = new Headers({'Content-Type': 'application/json' });
        return this.http.put(this.putUrl, JSON.stringify(ticket), {headers})
                        .toPromise()
                        .then(res => res.json())
                        .catch(this.handleError);
    }

    deleteTicket(ticket:ITicket): Promise<ITicket> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let url = `${this.deleteUrl}/${ticket.ticketId}`;
        return this.http.delete(url,{headers})
                    .toPromise()
                    .then(res => ticket)
                    .catch(this.handleError);
        
    }

    private handleError(error: any):Promise<any> {
        console.log('HandleError', error);
        return Promise.reject(error.message || error);
    }
///////////////////////////////////message///////////////////////////////////////////////
getAllMessages(ticket:ITicket): Promise<IMessage[]> {
        let url = `${this.showUrl}/${ticket.ticketId}`;
        return this.http.get(url)
                 .toPromise()
                 .then(res => res.json())
                 .catch(this.handleError);
                }

addMessage(message:IMessage): Promise<IMessage> {
        let headers = new Headers({'Content-Type': 'application/json' });
         let url = `${this.addMessageUrl}/${message.idTicket}`;
        return this.http.post(url, JSON.stringify(message), {headers})
                        .toPromise()
                        .then(res => res.json())
                        .catch(this.handleError);
    }

}