import {Component, OnInit, OnDestroy, ViewChild} from "@angular/core";
import {CORE_DIRECTIVES} from "@angular/common";
import {Ticket} from "./ticket.interface";
import {TicketService} from "./ticket.service";
import {PageCreator} from "../../../shared/services/page.creator.interface";
import "rxjs/Rx";
import {MODAL_DIRECTIVES, BS_VIEW_PROVIDERS, ModalDirective} from "ng2-bootstrap/ng2-bootstrap";

@Component({
    selector: 'my-ticket',
    templateUrl: 'src/app/user/ticket/ticket.html',
    providers: [TicketService],
    directives: [MODAL_DIRECTIVES, CORE_DIRECTIVES],
    viewProviders: [BS_VIEW_PROVIDERS]
})
export class UserTicketComponent implements OnInit, OnDestroy {

    private tickets:Ticket[] = [];
    private selectedTicket:Ticket = {ticketId: null, name: '', description: '', time: ''};
    private newTicket:Ticket = {ticketId: null, name: '', description: '', time: ''};
    private pageCreator:PageCreator<Ticket>;
    private pageNumber:number = 1;
    private pageList:Array<number> = [];
    private totalPages:number;
    @ViewChild('delModal') public delModal:ModalDirective;
    @ViewChild('createModal') public createModal:ModalDirective;
    @ViewChild('editModal') public editModal:ModalDirective;
    active:boolean = true;
    order:boolean = true;

    private ticketId:number;

    constructor(private _ticketService:TicketService) {
    }

    openEditModal(ticket:Ticket) {
        this.selectedTicket = ticket;
        console.log('selected ticket: ' + this.selectedTicket);
        this.editModal.show();
    }

    isDateValid(date:string):boolean {
        return /\d{4}-\d{2}-\d{2}/.test(date);
    }

    onEditTicketSubmit() {
        this.active = false;
        console.log('saving ticket: ' + this.selectedTicket);
        this._ticketService.editAndSave(this.selectedTicket);
        this._ticketService.getAllTickets(this.pageNumber);
        this.editModal.hide();
        setTimeout(() => this.active = true, 0);
    }

    closeEditModal() {
        console.log('closing edit modal');
        this.editModal.hide();
    }

    openCreateModal() {
        this.createModal.show();
    }

    onCreateTicketSubmit() {
        this.active = false;
        console.log('creating ticket');
        this._ticketService.addTicket(this.newTicket);
        this._ticketService.getAllTickets(this.pageNumber);
        this.createModal.hide();
        setTimeout(() => this.active = true, 0);
    }

    closeCreateModal() {
        console.log('closing create modal');
        this.editModal.hide();
    }

    openDelModal(id:number) {
        this.ticketId = id;
        console.log('show', this.ticketId);
        this.delModal.show();
    }

    closeDelModal() {
        console.log('delete', this.ticketId);
        this._ticketService.deleteTicketById(this.ticketId);
        this.getTicketsByPageNum(this.pageNumber);
        this.delModal.hide();
    }

    ngOnInit():any {
        this.getTicketsByPageNum();
    }

    getTicketsByPageNum() {
        this.pageNumber = +pageNumber;
        this.emptyArray();
        return this._ticketService.getAllTickets()
            .subscribe((data) => {
                    this.tickets = data;
                    console.log(this.tickets);
                },
                (error) => {
                    console.error(error)
                });
    };

    prevPage() {
        this.pageNumber = this.pageNumber - 1;
        this.getTicketsByPageNum(this.pageNumber)
    }

    nextPage() {
        this.pageNumber = this.pageNumber + 1;
        this.getTicketsByPageNum(this.pageNumber)
    }

    emptyArray() {
        while (this.pageList.length) {
            this.pageList.pop();
        }
    }

    preparePageList(start:number, end:number) {
        for (let i = start; i <= end; i++) {
            this.pageList.push(i);
        }
    }


    sortBy(name:string) {
        console.log('sorted by ', name);
        this.order = !this.order;
        console.log('order by asc', this.order);
        this.emptyArray();
        this._ticketService.getAllTicketsSorted(this.pageNumber, name, this.order)
            .subscribe((data) => {
                    this.pageCreator = data;
                    this.tickets = data.rows;
                    this.preparePageList(+this.pageCreator.beginPage,
                        +this.pageCreator.endPage);
                    this.totalPages = +data.totalPages;
                },
                (error) => {
                    console.error(error)
                });
    }


    ngOnDestroy():any {
        //this.subscriber.unsubscribe();
    }

}