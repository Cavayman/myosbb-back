import { Component, Output, Input, EventEmitter, OnInit, ViewChild } from '@angular/core';
import {CORE_DIRECTIVES} from '@angular/common';
import { ITicket, Ticket} from '../ticket';
import {MODAL_DIRECTIVES, BS_VIEW_PROVIDERS} from 'ng2-bootstrap/ng2-bootstrap';
import {ModalDirective} from "ng2-bootstrap/ng2-bootstrap";
@Component({
    selector: 'ticket-del-form',
    templateUrl: './src/app/user/ticket/ticket_form/ticket-del-form.html',
    directives:[MODAL_DIRECTIVES, CORE_DIRECTIVES],
    viewProviders: [BS_VIEW_PROVIDERS]
})
export class TicketDelFormComponent {
    @Output() delete: EventEmitter<ITicket>;
    private ticket: ITicket;
    @ViewChild('delModal') delModal:ModalDirective;

    openDelModal(ticket:ITicket): void {
        this.ticket = ticket;
        this.delModal.show();   
    }

    constructor() {
        this.delete = new EventEmitter<ITicket>();
    }

   delTicket(): void {
        this.delete.emit(this.ticket);
    }
}