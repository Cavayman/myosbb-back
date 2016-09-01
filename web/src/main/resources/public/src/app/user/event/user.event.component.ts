import {Component, OnInit, OnDestroy, ViewChild} from "@angular/core";
import {CORE_DIRECTIVES} from "@angular/common";
import {Event} from "./event.interface";
import {EventService} from "./event.service";
import {PageCreator} from "../../../shared/services/page.creator.interface";
import "rxjs/Rx";
import {MODAL_DIRECTIVES, BS_VIEW_PROVIDERS, ModalDirective} from "ng2-bootstrap/ng2-bootstrap";
import {TranslatePipe} from "ng2-translate/ng2-translate";
import {CapitalizeFirstLetterPipe} from "../../../shared/pipes/capitalize-first-letter";
import {Timestamp} from "rxjs/operator/timestamp";
import {SelectItem} from "../../../shared/models/ng2-select-item.interface";
import {PeriodicityItems} from "../../../shared/models/periodicity.const";
import {HeaderComponent} from "../../header/header.component";
import {DROPDOWN_DIRECTIVES} from "ng2-bs-dropdown/dropdown";
import {SELECT_DIRECTIVES} from "ng2-select";
import {ActiveFilter} from "../../../shared/pipes/active.filter";

@Component({
    selector: 'my-event',
    templateUrl: 'src/app/user/event/event.html',
    providers: [EventService],
    directives: [MODAL_DIRECTIVES, CORE_DIRECTIVES, DROPDOWN_DIRECTIVES, SELECT_DIRECTIVES],
    viewProviders: [BS_VIEW_PROVIDERS],
    pipes: [TranslatePipe, CapitalizeFirstLetterPipe, ActiveFilter]
})
export class UserEventComponent implements OnInit, OnDestroy {

    private events:Event[];
    private selectedEvent:Event = new Event;
    private newEvent:Event = new Event;
    private pageCreator:PageCreator<Event>;
    private pageNumber:number = 1;
    private pageList:Array<number> = [];
    private totalPages:number;
    @ViewChild('delModal') public delModal:ModalDirective;
    @ViewChild('delAllModal') public delAllModal:ModalDirective;
    @ViewChild('createModal') public createModal:ModalDirective;
    @ViewChild('editModal') public editModal:ModalDirective;
    active:boolean = true;
    order:boolean = true;

    private id:number;
    private repeat: SelectItem[] = [];

    constructor(private _eventService:EventService) {
    }

    ngOnInit():any {
        console.log("init event cmp");
        console.log("repeat items:", PeriodicityItems);
        for (let i=0; i<PeriodicityItems.length; i++){
            this.repeat.push(PeriodicityItems[i]);
        }
        this.getRepeatTranslation();
        console.log('readable repeat: ', this.repeat);
        this.getEventsByPageNum(this.pageNumber);
    }

    public onSelectRepeat(value:SelectItem):void {
        console.log("value: ", value);
        this.newEvent.repeat = this.backToConst(value);
        this.selectedEvent.repeat = this.backToConst(value);
        console.log("set repeat: ", this.newEvent.repeat);
    }

    getRepeatTranslation(){
        console.log("got lang",  HeaderComponent.translateService.currentLang);
        for (let i=0; i < this.repeat.length; i++){
            HeaderComponent.translateService.get(this.repeat[i].text)
                .subscribe((data : string) => {
                    this.repeat[i].text = data;
                    console.log("repeat =", this.repeat[i]);
                })
        }
        console.log("repeat: ", this.repeat);
    }

    backToConst(item: SelectItem): string{
        var items : SelectItem[] =
            [{id: 1, text: 'ONE_TIME'},
                {id: 2, text: 'PERMANENT_DAYLY'},
                {id: 3, text: 'PERMANENT_WEEKLY'},
                {id: 4, text: 'PERMANENT_MONTHLY'},
                {id: 5, text: 'PERMANENT_YEARLY'}];
        for (let i=0; i<items.length; i++){
            if (item.id === items[i].id) {
                console.log("const: ", items[i].text);
                return items[i].text;
            }
        }
    }

    isDateValid(start:Timestamp, end:Timestamp):boolean {
        return end > start;
    }

    refresh() {
        console.log('refreshing...');
        this.getEventsByPageNum(this.pageNumber);
    }

    openEditModal(event:Event) {
        this.selectedEvent = event;
        console.log('selected event: ' + this.selectedEvent);
        this.editModal.show();
    }

    onEditEventSubmit() {
        this.active = false;
        console.log('saving event: ' + this.selectedEvent);
        this._eventService.editAndSave(this.selectedEvent);
        this._eventService.getAllEvents(this.pageNumber);
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

    onCreateEventSubmit() {
        this.active = false;
        console.log('creating event');
        this._eventService.addEvent(this.newEvent);
        this.createModal.hide();
        setTimeout(() => this.active = true, 0);
        this.refresh();
    }

    closeCreateModal() {
        console.log('closing create modal');
        this.createModal.hide();
    }

    openDelModal(id:number) {
        this.id = id;
        console.log('show', this.id);
        this.delModal.show();
    }

    closeDelModal() {
        console.log('delete', this.id);
        this._eventService.deleteEventById(this.id);
        this._eventService.getAllEvents(this.pageNumber);
        this.getEventsByPageNum(this.pageNumber);
        this.delModal.hide();
    }

    openDelAllModal() {
        this.delAllModal.show();
    }

    closeDelAllModal() {
        console.log('delete all');
        this._eventService.deleteAllEvents();
        this._eventService.getAllEvents(this.pageNumber);
        this.getEventsByPageNum(this.pageNumber);
        this.delAllModal.hide();
    }

    getEventsByPageNum(pageNumber:number) {
        this.pageNumber = +pageNumber;
        this.emptyArray();
        return this._eventService.getAllEvents(this.pageNumber)
            .subscribe((data) => {
                    this.pageCreator = data;
                    this.events = data.rows;
                    this.preparePageList(+this.pageCreator.beginPage,
                        +this.pageCreator.endPage);
                    this.totalPages = +data.totalPages;
                },
                (error) => {
                    console.error(error)
                });
    };

    prevPage() {
        this.pageNumber = this.pageNumber - 1;
        this.getEventsByPageNum(this.pageNumber)
    }

    nextPage() {
        this.pageNumber = this.pageNumber + 1;
        this.getEventsByPageNum(this.pageNumber)
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

    sortBy(title:string) {
        console.log('sorted by ', title);
        this.order = !this.order;
        console.log('order by asc', this.order);
        this.emptyArray();
        this._eventService.getAllEventsSorted(this.pageNumber, title, this.order)
            .subscribe((data) => {
                    this.pageCreator = data;
                    this.events = data.rows;
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

    onSearch(search:string){
        console.log("inside search: search param" + search);
        this._eventService.findEventsByNameOrAuthorOrDescription(search)
            .subscribe((events) => {
                console.log("data: " + events);
                this.events = events;
            });
    }
}