import {Component, OnInit,Output,Input,EventEmitter} from '@angular/core';
import {CORE_DIRECTIVES} from '@angular/common';
import {HTTP_PROVIDERS} from "@angular/http";
import {MODAL_DIRECTIVES, BS_VIEW_PROVIDERS} from 'ng2-bootstrap/ng2-bootstrap';
import {ModalDirective} from "ng2-bootstrap/ng2-bootstrap";
import {Observable} from 'rxjs/Observable';
import 'rxjs/Rx';
import {Message, IMessage,} from './message';
import {Ticket, ITicket,TicketState} from '..//..//ticket/ticket';
import { MessageService } from './message.service';
//import { TicketAddFormComponent } from './ticket_form/ticket-add-form.component';
//import { TicketEditFormComponent } from './ticket_form/ticket-edit-form.component';
//import { TicketDelFormComponent } from './ticket_form/ticket-del-form.component';

@Component({
    selector: 'message',
    templateUrl: './src/app/user/ticket/ticket.component.html',
    providers: [HTTP_PROVIDERS, MessageService],
    directives: [MODAL_DIRECTIVES, CORE_DIRECTIVES],
    viewProviders: [BS_VIEW_PROVIDERS]
})

export class MessageComponent implements OnInit {
    @Output() showMessage: EventEmitter<IMessage>;
    //@Input() ticket:ITicket;
    messageArr:IMessage[];
 private ticket: ITicket;

constructor( private messageService: MessageService) { 
        this.messageArr = [];
        this.showMessage = new EventEmitter<IMessage>();
        console.log("ggggggggggggggggggggggggggggggggs");
    }
    

 ngOnInit() {
    //    this.ticketService.getAllTicket().then(ticketArr => this.ticketArr = ticketArr);
    }

   initMessage(ticket:ITicket): void {
       this.messageService.getAllMessages(ticket).then(messageArr => this.messageArr = messageArr);
    }

}