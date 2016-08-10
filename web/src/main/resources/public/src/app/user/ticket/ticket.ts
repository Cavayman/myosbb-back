import {DatePipe} from '@angular/common' ;
import {Message} from './message/message' ;

export interface ITicket {
    ticketId: number;
    name: string;
    description: string;
    state: TicketState;
    message:Message[];
    time:string;
}

export class Ticket implements ITicket {
    ticketId:number;
    name:string;
    description:string;
    state:TicketState;
    message:Message[];
    time:string;


    constructor(name:string, description:string, state:TicketState) {
        this.name = name;
        this.description = description;
        this.state = state;
      //  this.time.valueOf();
      //  var datePipe = new DatePipe();
      //  this.time = datePipe.transform(new Date(), 'yyyy-MM-dd HH:mm:ss');
      //  //////////////....////....//.../.../////....////....////.../////////////this.time = new Date(2012,12,12).toLocaleString();
      //  let d = new Date().toLocaleString();
      //  this.time = new Date().format('yyyy-MM-dd');// HH:mm:ss
    }

      //  this.time = new Date().format(this.time || 'yyyy-mm-dd').toString();
}

export enum TicketState{
    NEW,
    IN_PROGRESS,
    DONE
}