import {RouterConfig} from "@angular/router";
import {Component, OnInit} from '@angular/core';
import {CORE_DIRECTIVES} from '@angular/common';
import {HTTP_PROVIDERS} from "@angular/http";
import {MODAL_DIRECTIVES, BS_VIEW_PROVIDERS} from 'ng2-bootstrap/ng2-bootstrap';
import {ModalDirective} from "ng2-bootstrap/ng2-bootstrap";
import {Observable} from 'rxjs/Observable';
import {NgSwitch} from '@angular/common';
import 'rxjs/Rx';
import { TicketService } from './ticket.service';
import {ROUTER_DIRECTIVES,RouterOutlet} from '@angular/router';
import {Message, IMessage,} from './message';
import {Ticket, ITicket,TicketState} from './ticket';
import {TicketComponent} from './ticket.component';
import { MessageService } from './single.ticket.service';
import { TicketAddFormComponent } from './ticket_form/ticket-add-form.component';
import { TicketEditFormComponent } from './ticket_form/ticket-edit-form.component';
import { TicketDelFormComponent } from './ticket_form/ticket-del-form.component';
import {ActivatedRoute} from "@angular/router";
import {Router} from '@angular/router';
import {Subscription} from "rxjs";
import {CurrentUserService} from "./../../../shared/services/current.user.service";
import {User} from './../user';
import {TranslatePipe} from "ng2-translate/ng2-translate";
@Component({
    selector: 'ticket',
    templateUrl: './src/app/user/ticket/single.ticket.component.html',
    providers: [HTTP_PROVIDERS, MessageService, TicketService],
    directives: [RouterOutlet, ROUTER_DIRECTIVES, MODAL_DIRECTIVES, CORE_DIRECTIVES, TicketAddFormComponent, TicketEditFormComponent, TicketDelFormComponent],
    viewProviders: [BS_VIEW_PROVIDERS],
    styleUrls: ['src/app/user/ticket/ticket.css'],
    pipes: [TranslatePipe]
})

export class MessageComponent implements OnInit {
    private message:IMessage;
    private messageArr:IMessage[] = [];
    private ticket:Ticket;
    private ticketId:number;
    private sub:Subscription;
    private currentUser:User;
    private updatedTicket:ITicket;
    private ticketState:string = 'new';
    private messDesc:string = "";
    private messText:string = "";


    constructor(private routeParams:ActivatedRoute,
                private ticketService:TicketService,
                private messageService:MessageService,
                private currentUserService:CurrentUserService,
                private router:Router) {
        this.currentUser = currentUserService.getUser();
        this.ticket = new Ticket("", "", TicketState.NEW);
        this.ticket.user = new User();
        this.ticket.assigned = new User();
        this.message = new Message("");
        this.ticket.messages = [];
        this.message.answers = [];
    }

    ngOnInit():any {
        this.sub = this.routeParams.params.subscribe((params)=> {
            this.ticketId = +params['id'];
            this.messageService.getTicketbyId(this.ticketId)
                .subscribe((data) =>  this.ticket = data,
                    this.messageService.getMessagesForTicket(this.ticketId)
                        .then(messages => this.messageArr = messages)
                ),
                (error) => console.error(error)
        });
    }

    private toTableTicket() {
        this.router.navigate(['home/user/ticket']);
    }

    createMessage(text:string):void {
        this.message.idTicket = this.ticketId;
        this.message.message = text;
        this.message.user = this.currentUserService.getUser();
        if (this.message.messageId == null) {
            this.messageService.addMessage(this.message).then(message => this.addMessage(message))
                .then(this.message.messageId = null);
        }
        else {
            this.editMessage(text);
        }
    }

    editMessage(text:string) {
        for (let i = 0; i < this.messageArr.length; i++) {
            if (this.messText == this.messageArr[i].message) {
                this.messageArr[i].message = text;
            }
        }
        this.message.message = text;
        this.messageService.editMessage(this.message)
            .then(this.message.messageId = null);
    }


    private addMessage(message:IMessage):void {
        this.messageArr.push(message);
    }

    deleteMessage(message:Message) {
        this.messageService.deleteMessage(message.messageId).then(this.delMessage(message));
    }

    private delMessage(message:Message) {
        let index = this.ticket.messages.indexOf(message);
        if (index > -1) {
            this.ticket.messages.splice(index, 1);
        }

    }

    editState(state:string) {
        console.log("STATE ggg:  " + state);
        if (state == 'IN_PROGRESS') {
            this.ticket.state = TicketState.IN_PROGRESS;
            this.ticketState = 'IN_PROGRESS';
        }
        else if (state == 'DONE') {
            this.ticket.state = TicketState.DONE;
            this.ticketState = 'DONE';
        }
        console.log("STATE:  " + this.ticket.state);

        this.messageService.editState(this.ticket);
    }


    editTicket(ticket:ITicket):void {
        this.ticketService.editTicket(ticket);
    }

    deleteTicket(ticket:ITicket):void {
        this.ticketService.deleteTicket(ticket).then(this.toTableTicket());
    }


    initEditMessage(message:Message) {
        this.message.messageId = message.messageId;
        this.messText = message.message;
        this.message = message;
        this.message.idTicket = this.ticket.ticketId;
    }

    getTime(time:Date):string {
        return new Date(time).toLocaleString();
    }

    initAddAnswer(parentMessage:Message) {

        this.message = new Message("");
        this.message.parentId = parentMessage.messageId;
        this.isAnswer(this.message.parentId);
        this.message.user = this.currentUser;
        console.log("mess" + this.message.parentId);

    }

    createAnswer(messageId:number, text:string) {
        this.message.parentId = messageId;
        this.message.user = this.currentUser;
        console.log("usseerrrr  " + JSON.stringify(this.message.user));

        this.message.message = text;
        this.messageService.addAnswer(this.message);
    }

    addAnswerToMessage(message:Message) {
        for (let i = 0; this.ticket.messages.length; i++) {
            if (this.ticket.messages[i].messageId == message.parentId) {
                this.ticket.messages[i].answers.push(message);
            }
        }
    }

    isAnswer(id:number):boolean {
        return this.message.parentId == id ? true : false;
    }

    isAssigned():boolean {
        return (this.ticket.assigned.firstName == this.currentUser.firstName && this.ticket.assigned.lastName == this.currentUser.lastName);
    }

    isCreator():boolean {
        return (this.ticket.user.firstName == this.currentUser.firstName && this.ticket.user.lastName == this.currentUser.lastName);
    }

    isMessageCreator(message:Message):boolean {
        console.log("ismeess creator" + message.user.firstName);
        return (message.user.firstName == this.currentUser.firstName && message.user.lastName == this.currentUser.lastName);
    }

}