import {RouterConfig,RouterOutlet} from "@angular/router";
import {Component, OnInit} from '@angular/core';
import {CORE_DIRECTIVES} from '@angular/common';
import {HTTP_PROVIDERS} from "@angular/http";
import {MODAL_DIRECTIVES, BS_VIEW_PROVIDERS} from 'ng2-bootstrap/ng2-bootstrap';
import {ModalDirective} from "ng2-bootstrap/ng2-bootstrap";
import {Observable} from 'rxjs/Observable';
import 'rxjs/Rx';
import {IMessage,Message} from './message/message'
import {Ticket, ITicket,TicketState} from './ticket';
import { TicketService } from './ticket.service';
import { TicketAddFormComponent } from './ticket_form/ticket-add-form.component';
import { TicketEditFormComponent } from './ticket_form/ticket-edit-form.component';
import { TicketDelFormComponent } from './ticket_form/ticket-del-form.component';
import { MessageComponent } from './message/message.component';
import { MessageService } from './message/message.service';
import {Router} from '@angular/router';
import {TranslatePipe} from "ng2-translate";
import {CapitalizeFirstLetterPipe} from "../../../shared/pipes/capitalize-first-letter";
import { RouteConfig, ROUTER_DIRECTIVES } from '@angular/router-deprecated';
/**
@RouteConfig( [
    {
        path: 'home/user/ticket/',
        children: [
            {path: '/message/:id',      name: 'messages',   component: MessageComponent},
            {path: '', component: MessageComponent}
        ],
        component: TicketComponent,
    }
]);
*/
/**
@RouteConfig([
  {path: '/message/:id',      name: 'messages',   component: MessageComponent},
  {path: '/ticket/message',      name: 'messages2',   component: MessageComponent}
])*/
@Component({
    selector: 'ticket',
    templateUrl: './src/app/user/ticket/ticket.component.html',
    providers: [HTTP_PROVIDERS, TicketService],
    directives: [RouterOutlet, ROUTER_DIRECTIVES,MODAL_DIRECTIVES, CORE_DIRECTIVES, TicketAddFormComponent, TicketEditFormComponent, TicketDelFormComponent],
    viewProviders: [BS_VIEW_PROVIDERS],
     pipes: [TranslatePipe, CapitalizeFirstLetterPipe]
})
export class TicketComponent implements OnInit { 
    
    messageService: MessageService;
    ticketArr:ITicket[]=[];
    updatedTicket:ITicket;
    messageArr:IMessage[]=[];
    message:Message;
messageComponent: MessageComponent;

    constructor( private ticketService: TicketService,private router: Router) {
    }
    
    ngOnInit() {
       // this.ticketService.getAllTicket().then(ticketArr => this.ticketArr = ticketArr);       
       this.ticketService.getAllTicket()      
     .then(ticketArr => this.ticketArr = ticketArr)
      .then(ticketArr => this.met());       
       
    }

private met():void{
   console.log("0000000000000000000000");
   console.log("this.ticketArr.length  = "+this.ticketArr.length);
    for(  let i=0; i<this.ticketArr.length; i++){
       let str = JSON.stringify(this.ticketArr[i].time, ['year','monthValue','dayOfMonth']).split(',');
       this.ticketArr[i].time = '';
          for(let j = 0;j<str.length;j++){
                 let st = str[j].split(':');
                this.ticketArr[i].time += st[1];
                    if(j<str.length-1){  this.ticketArr[i].time +='-';}
        }
    this.ticketArr[i].time = this.ticketArr[i].time.substring(0, this.ticketArr[i].time.length - 1);
}}

    private initUpdatedTicket(ticket:ITicket): void {
        this.updatedTicket = ticket;
    }

    createTicket(ticket:ITicket): void {
        this.ticketService.addTicket(ticket).then(ticket => this.addTicket(ticket));//.then(ticketArr => this.met());       ;
       console.log("id: "+ticket.ticketId+"   name:  "+ticket.name+ "   дата:  "+ticket.time);
    }

    editTicket(ticket:ITicket): void {
    //    let str = JSON.parse(ticket.time, function(key,value){
    //        if(key == 'year'){ ticket.time = value;}
    //        if(key == 'dayOfMonth'){ ticket.time += value;}
    //        console.log(ticket.time);
    //        
    //    });
    
    //let str = JSON.stringify(ticket.time);

   
    
        this.ticketService.editTicket(ticket);
    }

   
    deleteTicket(ticket:ITicket): void {
       this.ticketService.deleteTicket(ticket).then(ticket => this.deleteTicketFromArr(ticket));
    }

    private addTicket(ticket:ITicket): void {
        this.ticketArr.push(ticket);
         this.met();       
    }

     private deleteTicketFromArr(ticket:ITicket): void {
         let index = this.ticketArr.indexOf(ticket);
         if(index > -1) {
            this.ticketArr.splice(index, 1);
         }
          this.ticketService.getAllTicket().then(ticketArr => this.ticketArr = ticketArr).then(ticketArr => this.met());       
    }

     getAllMessages(ticket:ITicket): void {
       console.log("iiiiiiiiiiiiiii");
       this.messageService.getAllMessages(ticket.ticketId).then(messageArr => this.messageArr = messageArr);
       console.log(this.messageArr.length);
       
    }

    addMessage(ticket:number,description:string,text:string):void{

        this.message = new Message(description,text,ticket);
     // this.updatedTicket.message.push(this.message);
        console.log(this.message.idTicket+"    "+this.message.message);


        this.messageService.addMessage(this.message);//.then(message => this.addMess(ticket,message));
    }

   private addMess(ticket:ITicket,message:IMessage):void{
       ticket.message.push(message);
    }

 singleTicket(id:number){
      console.log("ticketId : "+ id);
     this.router.navigate( ['messages',id]);
      console.log("singleTicket method in ticket component");
       
    }
  
}