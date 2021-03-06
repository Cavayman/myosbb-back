import { Component, Output, Input, EventEmitter, OnInit, ViewChild} from '@angular/core';
import {CORE_DIRECTIVES,FORM_DIRECTIVES, FormBuilder, Control, ControlGroup, Validators} from '@angular/common';
import { ITicket,Ticket,TicketState} from '../ticket';
import {MODAL_DIRECTIVES, BS_VIEW_PROVIDERS} from 'ng2-bootstrap/ng2-bootstrap';
import {ModalDirective} from "ng2-bootstrap/ng2-bootstrap";
import {User} from './../../user';
import {CurrentUserService} from "./../../../../shared/services/current.user.service";
import { TicketService } from './../ticket.service';
import { TicketFilter } from './../ticket.filter';
import {TranslatePipe} from "ng2-translate/ng2-translate";
@Component({
    selector: 'ticket-edit-form',
    templateUrl: './src/app/user/ticket/ticket_form/ticket-edit-form.html',
    directives: [MODAL_DIRECTIVES, CORE_DIRECTIVES, FORM_DIRECTIVES],
    viewProviders: [BS_VIEW_PROVIDERS],
    pipes: [TicketFilter, TranslatePipe],
    styleUrls: ['src/app/user/ticket/ticket.css']
})
export class TicketEditFormComponent implements OnInit {
    @Output() update:EventEmitter<Ticket>;
    @Input() ticket:ITicket;
    @Input() state:TicketState;
    @ViewChild('editModal')
    editModal:ModalDirective;
    states:string[];

   stateTicket = "NEW";
    userAssignArr:User[] = [];
    assign:User;
    creatingForm:ControlGroup;
    nameInput:Control;
    descriptionInput:Control;
    assignInput:Control;
    submitAttempt:boolean;
    nameTicket:string = '';
    descriptionTicket:string = '';
    assignTicket:string = '';

    constructor(private ticketService:TicketService,
                private currentUserService:CurrentUserService,
                private builder:FormBuilder) {
        this.ticket = new Ticket("", "", TicketState.NEW);
        this.update = new EventEmitter<Ticket>();
        this.states = ["NEW", "IN_PROGRESS", "DONE"];
        this.assign = new User();
        this.ticket.assigned = new User();
        this.submitAttempt = false;
        this.nameInput = new Control('', Validators.minLength(20));
        this.descriptionInput = new Control('', Validators.minLength(60));
        this.assignInput = new Control('', Validators.required);
        this.creatingForm = builder.group({
            nameInput: this.nameInput,
            descriptionInput: this.descriptionInput,
            assignInput: this.assignInput
        });


    }

    openEditModal() {
        this.editModal.show();
    }


    ngOnInit() {
        this.getAllUsers();
    }

    isEmptyName():boolean {
        return this.nameTicket == '' ? false : true;
    }

    isEmptyDescription():boolean {
        return this.descriptionTicket == '' ? false : true;
    }

    isFindAssign():boolean {
        if (this.getAssignedId(this.assignTicket) == null) {
            return false;
        }
            return true;
    }


    getAllUsers() {
        return this.ticketService.getAllUsers()
            .then(userAssignArr => this.userAssignArr = userAssignArr);
    }

    toggleSubmitAttempt() {
        this.submitAttempt = true;
    }

    closeEditModal() {
        this.submitAttempt = false;
        this.clearEditModal();
        this.editModal.hide();
    }

    clearEditModal() {
        this.nameTicket = '';
        this.descriptionTicket = '';
        this.assignTicket = '';

    }

    onEditTicket() {
        if (this.nameInput.valid && this.descriptionInput.valid && this.assignInput.valid && this.isFindAssign()) {
            this.update.emit(this.editTicket());
            this.closeEditModal();
        }
    }

    editState(state:string) {
        if (state == 'NEW') {
           return TicketState.NEW;
        }
        if (state == 'IN_PROGRESS') {
           return TicketState.IN_PROGRESS;
        }
        else if (state == 'DONE') {
            return TicketState.DONE;
        }
        

    //    this.messageService.editState(this.ticket)
       // .this.ticketService.sendEmailState(this.ticket.ticketId);
    }

    initUpdatedTicket(ticket:Ticket) {
        console.log("is ticketttt"+ticket.ticketId);
        this.ticket = ticket;
        this.nameTicket = ticket.name;
        this.descriptionTicket = ticket.description;
        this.assignTicket = ticket.assigned.firstName + " " + ticket.assigned.lastName;
      //  this.stateTicket = ticket.state;
    }

    editTicket():Ticket {
        console.log("edit ticket");
        let ticket = new Ticket(this.nameTicket, this.descriptionTicket, TicketState.NEW);
        ticket.ticketId = this.ticket.ticketId;
        ticket.user = this.currentUserService.getUser();
        ticket.assigned = this.getAssignedId(this.assignTicket);
        ticket.state = this.editState(this.stateTicket);
        console.log("edit state  "+ this.stateTicket);
        return ticket;
    }

    getAssignedId(assign:string):User {
        let str = assign.split(' ');
        for (let i = 0; i < this.userAssignArr.length; i++) {
            if (str[0] == this.userAssignArr[i].firstName && str[1] == this.userAssignArr[i].lastName) {
                return this.userAssignArr[i];
            }
        }
    }

}

