import {Component, OnInit} from '@angular/core';
import {CORE_DIRECTIVES} from '@angular/common';
import {HTTP_PROVIDERS} from "@angular/http";
import {MODAL_DIRECTIVES, BS_VIEW_PROVIDERS} from 'ng2-bootstrap/ng2-bootstrap';
import {ModalDirective} from "ng2-bootstrap/ng2-bootstrap";
import {Observable} from 'rxjs/Observable';
import 'rxjs/Rx';

import {Ticket, ITicket,TicketState} from './ticket';
import { TicketService } from './ticket.service';
import { TicketAddFormComponent } from './ticket_form/ticket-add-form.component';
import { TicketEditFormComponent } from './ticket_form/ticket-edit-form.component';
import { TicketDelFormComponent } from './ticket_form/ticket-del-form.component';

@Component({
    selector: 'ticket',
    templateUrl: './src/app/user/ticket/ticket.component.html',
    providers: [HTTP_PROVIDERS, TicketService],
    directives: [MODAL_DIRECTIVES, CORE_DIRECTIVES, TicketAddFormComponent, TicketEditFormComponent, TicketDelFormComponent],
    viewProviders: [BS_VIEW_PROVIDERS]
})
export class TicketComponent implements OnInit { 
    
    ticketArr:ITicket[];
    updatedTicket:ITicket;

    constructor( private ticketService: TicketService) { 
        this.ticketArr = [];
    }
    
    ngOnInit() {
        this.ticketService.getAllTicket().then(ticketArr => this.ticketArr = ticketArr);
    }

    private initUpdatedTicket(ticket:ITicket): void {
        this.updatedTicket = ticket;
    }

    createTicket(ticket:ITicket): void {
        this.ticketService.addTicket(ticket).then(ticket => this.addTicket(ticket));
    }

    editTicket(ticket:ITicket): void {
        this.ticketService.editTicket(ticket);
    }

    deleteTicket(ticket:ITicket): void {
       this.ticketService.deleteTicket(ticket).then(ticket => this.deleteTicketFromArr(ticket));
       // this.ticketService.deleteTicket(ticket);
      //  this.ticketService.getAllTicket().then(ticketArr => this.ticketArr = ticketArr);
    }

    private addTicket(ticket:ITicket): void {
        this.ticketArr.push(ticket);
    }

     private deleteTicketFromArr(ticket:ITicket): void {
         let index = this.ticketArr.indexOf(ticket);
         if(index > -1) {
            this.ticketArr.splice(index, 1);
         }
          this.ticketService.getAllTicket().then(ticketArr => this.ticketArr = ticketArr);
    }
}