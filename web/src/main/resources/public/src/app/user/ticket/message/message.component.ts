import {RouterConfig} from "@angular/router";
import {Component, OnInit} from '@angular/core';
import {CORE_DIRECTIVES} from '@angular/common';
import {HTTP_PROVIDERS} from "@angular/http";
import {MODAL_DIRECTIVES, BS_VIEW_PROVIDERS} from 'ng2-bootstrap/ng2-bootstrap';
import {ModalDirective} from "ng2-bootstrap/ng2-bootstrap";
import {Observable} from 'rxjs/Observable';
import 'rxjs/Rx';
import { TicketService } from './/..//ticket.service';
import {ROUTER_DIRECTIVES,RouterOutlet} from '@angular/router';
import {Message, IMessage,} from './message';
import {Ticket, ITicket,TicketState} from '../../ticket/ticket';
import {TicketComponent} from '../../ticket/ticket.component';
import { MessageService } from './message.service';
import { RouteParams, Router } from '@angular/router-deprecated';
@Component({
    selector: 'message',
    templateUrl: './src/app/user/ticket/message/message.component.html',
    providers: [HTTP_PROVIDERS, MessageService],
    directives: [MODAL_DIRECTIVES, CORE_DIRECTIVES,RouterOutlet],
    viewProviders: [BS_VIEW_PROVIDERS]
})

export class MessageComponent implements OnInit {
    messageArr:IMessage[];
 private ticket: ITicket;
 ticketId:number;

 

constructor(private router: Router,
    private routeParams: RouteParams,
    private messageService: MessageService) { 
        this.messageArr = [];
       // this.showMessage = new EventEmitter<IMessage>();
        this.ticketId = +routeParams.get('id');

        console.log("message constructor");
        
        
    }
    
    initTicket(ticketId:number):void{
        this.ticketId = ticketId;
        console.log("ticketId = "+ ticketId);
        
    }

  ngOnInit() {
      let id = this.routeParams.get('id');  
      console.log("log Id: "+ id);
      
      this.messageService.getAllMessages(parseInt(id)).then(messageArr => this.messageArr = messageArr)
    //this.service.getHero(id).then(hero => this.hero = hero);
    
    }

   initMessage(ticket:ITicket): void {
         let id = this.routeParams.get('id');  
       this.messageService.getAllMessages(parseInt(id)).then(messageArr => this.messageArr = messageArr);
    }


  

}