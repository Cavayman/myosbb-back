import {RouterConfig,RouterOutlet} from "@angular/router";
import {Component, OnInit} from '@angular/core';
import {CORE_DIRECTIVES} from '@angular/common';
import {MODAL_DIRECTIVES, BS_VIEW_PROVIDERS} from 'ng2-bootstrap/ng2-bootstrap';
import {ModalDirective} from "ng2-bootstrap/ng2-bootstrap";
import {Observable} from 'rxjs/Observable';
import 'rxjs/Rx';
import {PageCreator} from "../../../shared/services/page.creator.interface";
import {IMessage,Message} from './message'
import {Ticket, ITicket,TicketState} from './ticket';
import { TicketService } from './ticket.service';
import { TicketAddFormComponent } from './ticket_form/ticket-add-form.component';
import { TicketEditFormComponent } from './ticket_form/ticket-edit-form.component';
import { TicketDelFormComponent } from './ticket_form/ticket-del-form.component';
import { MessageComponent } from './single.ticket.component';
import { MessageService } from './single.ticket.service';
import {Router} from '@angular/router';
import {TranslatePipe} from "ng2-translate";
import {CapitalizeFirstLetterPipe} from "../../../shared/pipes/capitalize-first-letter";
import {RouteConfig, ROUTER_DIRECTIVES } from '@angular/router-deprecated';
import {User} from './../user';
import {CurrentUserService} from "./../../../shared/services/current.user.service";

@Component({
    selector: 'ticket',
    templateUrl: './src/app/user/ticket/ticket.component.html',
    providers: [ TicketService, MessageComponent, MessageService],
    directives: [RouterOutlet, ROUTER_DIRECTIVES, MODAL_DIRECTIVES, CORE_DIRECTIVES, TicketAddFormComponent, TicketEditFormComponent, TicketDelFormComponent],
    viewProviders: [BS_VIEW_PROVIDERS],
    pipes: [TranslatePipe],
    styleUrls: ['src/app/user/ticket/ticket.css']
})


export class TicketComponent implements OnInit {

    private messageService:MessageService;
    private ticketArr:ITicket[] = [];
    private updatedTicket:ITicket;
    private messageArr:IMessage[] = [];
    private message:Message;
    private messageComponent:MessageComponent;
    private currentUser:User;
    private userAssignArr:User[] = [];
    private dates:string[] = [];
    private pageCreator:PageCreator<Ticket>;
    private pageNumber:number = 1;
    private pageList:Array<number> = [];
    private totalPages:number;
    private pending = false;
    private selectedRow:number = 10;
    private order:boolean = false;
    nameSort:string = "time";
    status:string = "";
    email:string = ""

    constructor(private ticketService:TicketService,
                messageComponent:MessageComponent,
                private currentUserService:CurrentUserService,
                private router:Router) {
        this.currentUser = currentUserService.getUser();
        console.log("user: " + this.currentUser);
    }

    ngOnInit() {
        this.getTicketsByPageNum(this.pageNumber, this.selectedRow);
    }

   getAllUsers() {
        return this.ticketService.getAllUsers()
            .then(userAssignArr => this.userAssignArr = userAssignArr)      
    }

    private initUpdatedTicket(ticket:ITicket):void {
        this.updatedTicket = ticket;
    }

    createTicket(ticket:ITicket):void {
        this.ticketService.addTicket(ticket).then(ticket => this.addTicket(ticket));
    }

    
    private addTicket(ticket:ITicket):void {
      //  this.ticketService.sendEmailAssign(ticket.ticketId);
        this.ticketArr.unshift(ticket);
    }

    editTicket(ticket:ITicket):void {
        this.ticketService.editTicket(ticket);
        let index = this.ticketArr.indexOf(ticket);
                
        if (index > -1) {
            this.ticketArr[index].state = ticket.state;
        }
    }


    deleteTicket(ticket:ITicket):void {
        this.ticketService.deleteTicket(ticket).then(ticket => this.deleteTicketFromArr(ticket));
    }


    private deleteTicketFromArr(ticket:ITicket):void {
        let index = this.ticketArr.indexOf(ticket);
        if (index > -1) {
            this.ticketArr.splice(index, 1);
        }
    }

    findTicketByName(name:string) {
        this.pending = true;
        return this.ticketService.findByNameDescription(this.pageNumber, this.selectedRow, this.nameSort, this.order, name)
            .subscribe((data) => {
                    this.pending = false;
                    this.pageCreator = data;
                    this.ticketArr = data.rows;
                    this.preparePageList(+this.pageCreator.beginPage,
                        +this.pageCreator.endPage);
                    this.totalPages = +data.totalPages;
                    this.dates = data.dates;
                },
                (error) => {
                    this.pending = false;
                    console.error(error)
                });
    }

  findMyTickets() {
        this.pending = true;
        this.email = this.currentUser.email;
        this.status="";
        return this.ticketService.findByUser(this.pageNumber, this.selectedRow, this.nameSort, this.order, this.email)
            .subscribe((data) => {
                    this.pending = false;
                    this.pageCreator = data;
                    this.ticketArr = data.rows;
                    this.preparePageList(+this.pageCreator.beginPage,
                        +this.pageCreator.endPage);
                    this.totalPages = +data.totalPages;
                    this.dates = data.dates;
                },
                (error) => {
                    this.pending = false;
                    console.error(error)
                });
    }

     findTicketByState(state:string) {
        this.pending = true;
        this.status = state;
        this.email ="";
        return this.ticketService.findByState(this.pageNumber, this.selectedRow, this.nameSort, this.order, state)
            .subscribe((data) => {
                    this.pending = false;
                    this.pageCreator = data;
                    this.ticketArr = data.rows;
                    this.preparePageList(+this.pageCreator.beginPage,
                        +this.pageCreator.endPage);
                    this.totalPages = +data.totalPages;
                    this.dates = data.dates;
                },
                (error) => {
                    this.pending = false;
                    console.error(error)
                });
    }

    singleTicket(id:number) {
        this.router.navigate(['home/user/ticket', id]);
    }


    getTime(time:Date):string {
        return new Date(time).toLocaleString();
    }

    selectRowNum(row:number) {
        console.log("selectRowNum");
        
        if(this.status != ""){
            this.findTicketByState(this.status);
        }
        else if(this.email != ""){
            this.findMyTickets();
        }
        else {this.getTicketsByPageNum(this.pageNumber, row);
        }
       
    }

    prevPage() {
        console.log("prevPage");
        
        this.pageNumber = this.pageNumber - 1;
        if(this.status != ""){
            this.findTicketByState(this.status);
        }
        else if(this.email != ""){
            this.findMyTickets();
        }
        else {        
        this.sortBy(this.nameSort);
        }
    }

    nextPage() {
        console.log("nextPage");
        
        this.pageNumber = this.pageNumber + 1;
        this.sortBy(this.nameSort);
    }

    initPageNum(pageNumber:number, selectedRow:number) {
        console.log("initPageNum");
        
        this.pageNumber = +pageNumber;
        this.selectedRow = +selectedRow;

        if(this.status != ""){
            this.findTicketByState(this.status);
        }
        else if(this.email != ""){
            this.findMyTickets();
        }
         else{  
        this.sortBy(this.nameSort);
         }
    }

    getTicketsByPageNum(pageNumber:number, selectedRow:number) {
        console.log("getTicketsByPageNum");
        
        this.pageNumber = +pageNumber;
        this.pending = true;
        this.selectedRow = +selectedRow;
        return this.ticketService.getTicketsByPage(this.pageNumber, this.selectedRow)
            .subscribe((data) => {
                    this.pending = false;
                    this.pageCreator = data;
                    this.ticketArr = data.rows;
                    this.preparePageList(+this.pageCreator.beginPage,
                        +this.pageCreator.endPage);
                    this.totalPages = +data.totalPages;
                    this.dates = data.dates;
                },
                (error) => {
                    this.pending = false;
                    console.error(error)
                });
    }

    emptyArray() {
        while (this.pageList.length) {
            this.pageList.pop();
        }
    }

    preparePageList(start:number, end:number) {
        this.emptyArray();
        for (let i = start; i <= end; i++) {
            this.pageList.push(i);
        }
    }

    sortBy(name:string) {
        console.log("sortBy");
        
        this.email = '';
        this.status ='';
        if (name != '') {
            this.nameSort = name;
            this.order = !this.order;
        }
        this.ticketService.getTicketsSorted(this.pageNumber, this.selectedRow, this.nameSort, this.order)
            .subscribe((data) => {
                    this.pageCreator = data;
                    this.ticketArr = data.rows;
                    this.preparePageList(+this.pageCreator.beginPage,
                        +this.pageCreator.endPage);
                    this.totalPages = +data.totalPages;
                },
                (error) => {
                    console.error(error)
                });
    }
}