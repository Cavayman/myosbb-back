import {Injectable} from "@angular/core";
import {Http, Headers} from "@angular/http";
import "rxjs/add/operator/map";
import "rxjs/add/operator/toPromise";
import ApiService = require("../../../shared/services/api.service");
import {User} from './../user';
import {ITicket,TicketState} from './ticket';

@Injectable()
export class TicketService {

    private deleteUrl:string = ApiService.serverUrl + '/restful/ticket';
    private postUrl:string = ApiService.serverUrl + '/restful/ticket';
    private putUrl:string = ApiService.serverUrl + '/restful/ticket';
    private getUrl:string = ApiService.serverUrl + '/restful/ticket';
    private getOneUrl:string = ApiService.serverUrl + '/restful/ticket';
    private getAssignUser:string = ApiService.serverUrl + '/restful/user/assigned';
    private getTicketByPage:string = ApiService.serverUrl + '/restful/ticket/page';
    private getUsers:string = ApiService.serverUrl + '/restful/user';
    private findTicketByName:string = ApiService.serverUrl + '/restful/ticket/findName';
    private sendEmailAssignUrl:string = ApiService.serverUrl + '/sendEmailAssign';
    private sendEmailStateUrl:string = ApiService.serverUrl + '/sendEmailState';
    private findTicketByState:string = ApiService.serverUrl + '/restful/ticket/state';
    private findTicketByUser:string = ApiService.serverUrl + '/restful/ticket/userEmail';
    private aaa:string = ApiService.serverUrl + '/sendEmailMail';
    constructor(private http:Http) {
    }


    getTicketsByPage(pageNumber:number, selectedRow:number):Observable<any> {
        return this.http.get(this.getTicketByPage + '?pageNumber=' + pageNumber + '&&sortedBy=time' + '&&rowNum=' + selectedRow)
            .map((response)=> response.json())
            .catch((error)=>Observable.throw(error));
    }

    getTicketsSorted(pageNumber:number, selectedRow:number, name:string, order:boolean):Observable<any> {
        return this.http.get(this.getTicketByPage + '?pageNumber=' + pageNumber + '&&sortedBy=' + name + '&&order=' + order + '&&rowNum=' + selectedRow)
            .map((response)=> response.json())
            .catch((error)=>Observable.throw(error));
    }

    findByNameDescription(pageNumber:number, selectedRow:number, name:string, order:boolean, findName:string):Observable<any> {
        return this.http.get(this.findTicketByName + '?pageNumber=' + pageNumber + '&&sortedBy=' + name + '&&order=' + order + '&&rowNum=' + selectedRow + '&&find=' + findName)
            .map((response)=> response.json())
            .catch((error)=>Observable.throw(error));
    }

    findByUser(pageNumber:number, selectedRow:number, name:string, order:boolean, email:string):Observable<any> {
        return this.http.get(this.findTicketByUser + '?pageNumber=' + pageNumber + '&&sortedBy=' + name + '&&order=' + order + '&&rowNum=' + selectedRow + '&&find=' + email)
            .map((response)=> response.json())
            .catch((error)=>Observable.throw(error));
    }

    findByState(pageNumber:number, selectedRow:number, name:string, order:boolean, findName:TicketState):Observable<any> {
        return this.http.get(this.findTicketByState + '?pageNumber=' + pageNumber + '&&sortedBy=' + name + '&&order=' + order + '&&rowNum=' + selectedRow + '&&findName=' + findName)
            .map((response)=> response.json())
            .catch((error)=>Observable.throw(error));
    }

    getAllTicket():Promise<ITicket[]> {
        return this.http.get(this.getUrl)
            .toPromise()
            .then(res => res.json())
            .catch(this.handleError);
    }

    getAllUsers():Promise<User[]> {
        return this.http.get(this.getUsers)
            .toPromise()
            .then(res => res.json())
            .catch(this.handleError);
    }

    getTicketbyId(ticketId:number):Promise<ITicket> {
        let url = `${this.getOneUrl}/${ticketId}`;
        return this.http.get(url)
            .toPromise()
            .then(res => res.json())
            .catch(this.handleError);
    }

    addTicket(ticket:ITicket):Promise<ITicket> {
        return this.http.post(this.postUrl, JSON.stringify(ticket))
            .toPromise()
            .then(res => res.json())
            .catch(this.handleError);
    }

    editTicket(ticket:ITicket):Promise<ITicket> {
        return this.http.put(this.putUrl, JSON.stringify(ticket))
            .toPromise()
            .then(res => res.json())
            .catch(this.handleError);
    }

    deleteTicket(ticket:ITicket):Promise<ITicket> {
        let url = `${this.deleteUrl}/${ticket.ticketId}`;
        return this.http.delete(url)
            .toPromise()
            .then(res => ticket)
            .catch(this.handleError);
    }

    findAssignUser(name:string) {
        let url = `${this.getAssignUser}/${name}`;
        return this.http.get(url)
            .toPromise()
            .then(res => res.json())
            .catch(this.handleError);
    }

    sendEmailAssign(ticketId:number){
        return this.http.post(this.sendEmailAssignUrl, JSON.stringify(ticketId))
            .toPromise()
            .then(res => res.json())
            .catch(this.handleError);
    }
   


    private handleError(error:any):Promise<any> {
        console.log('HandleError', error);
        return Promise.reject(error.message || error);
    }


}