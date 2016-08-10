import { Component, Output, Input, EventEmitter, OnInit, ViewChild } from '@angular/core';
import {CORE_DIRECTIVES} from '@angular/common';
import { ITicket,Ticket,TicketState} from '../ticket';
import {MODAL_DIRECTIVES, BS_VIEW_PROVIDERS} from 'ng2-bootstrap/ng2-bootstrap';
import {ModalDirective} from "ng2-bootstrap/ng2-bootstrap";
@Component({
    selector: 'ticket-edit-form',
    templateUrl: './src/app/user/ticket/ticket_form/ticket-edit-form.html',
    directives:[MODAL_DIRECTIVES, CORE_DIRECTIVES],
    viewProviders: [BS_VIEW_PROVIDERS]
})
export class TicketEditFormComponent implements OnInit{
    @Output() update: EventEmitter<Ticket>;
    @Input() ticket:ITicket;
    @Input() state:TicketState;
    @ViewChild('editModal')
    editModal:ModalDirective;
    states:string[];


    openEditModal() {
        this.editModal.show();  
    }

    constructor() {
        this.update = new EventEmitter<Ticket>();
        this.states= ["NEW", "IN_PROGRESS", "DONE"];
    }

    ngOnInit() {
if(this.ticket === undefined){
this.ticket = new Ticket("", "",TicketState.DONE);
}
    }

    editTicket(name:string, description:string,state:TicketState) {
        if (name) {
           this.ticket.name = name;
           this.ticket.description = description;
           this.ticket.state = state;
           this.update.emit(this.ticket);
        }
    }
}