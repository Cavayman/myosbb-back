import { Component, Output, Input, EventEmitter, OnInit, ViewChild } from '@angular/core';
import {CORE_DIRECTIVES} from '@angular/common';
import { ITicket, Ticket,TicketState} from '../ticket';
import {MODAL_DIRECTIVES, BS_VIEW_PROVIDERS} from 'ng2-bootstrap/ng2-bootstrap';
import {ModalDirective} from "ng2-bootstrap/ng2-bootstrap";
@Component({
    selector: 'ticket-add-form',
    templateUrl: './src/app/user/ticket/ticket_form/ticket-add-form.html',
    directives:[MODAL_DIRECTIVES, CORE_DIRECTIVES],
    viewProviders: [BS_VIEW_PROVIDERS]
})
export class TicketAddFormComponent implements OnInit{
    @Output() created: EventEmitter<Ticket>;
    @Input() ticket:ITicket;

    @ViewChild('addModal')
    addModal:ModalDirective;  

    openAddModal() {
        this.addModal.show();  
    }

    constructor() {
        this.created = new EventEmitter<Ticket>();
    }

    ngOnInit() {
        if(this.ticket === undefined){
            this.ticket = new Ticket("", "",TicketState.NEW);
        }
    }

    createTicket(name:string, description:string) {
        if (name) {
            let ticket = new Ticket(name, description, TicketState.NEW);
            this.created.emit(ticket);
        }
    }
}