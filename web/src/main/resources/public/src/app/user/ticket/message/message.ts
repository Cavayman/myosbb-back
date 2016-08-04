
import {DatePipe} from '@angular/common' ;
import { ITicket,Ticket } from '..//..//ticket/ticket' ;

export interface IMessage {
    messageId: number;
    description: string;
    message: string;
    idTicket:Ticket;
 //time:string;
}

export class Message implements IMessage {
    messageId: number;
    description: string;
    message: string;
    idTicket:Ticket;
   // time:string;


    constructor(  description:string, message:string, idTicket: Ticket) {
        this.message = message;
        this.description = description;
        this.idTicket = idTicket;
    }}


