import {Component, Input, Output, EventEmitter} from "@angular/core";
import {EventService} from "./event.service";
import {Event} from "./event.interface";
import {CapitalizeFirstLetterPipe} from "../../../shared/pipes/capitalize-first-letter";
import {TranslatePipe} from "ng2-translate/ng2-translate";
import {SelectItem} from "../../../shared/models/ng2-select-item.interface";
import {SELECT_DIRECTIVES} from "ng2-select/ng2-select";
import {FORM_DIRECTIVES, NgClass, CORE_DIRECTIVES} from "@angular/common";
import {BUTTON_DIRECTIVES} from "ng2-bootstrap/ng2-bootstrap";

@Component({
    selector: 'select-event',
    template: `<div class="form-group">
                          <div>
                             <label> {{ 'company' | translate | capitalize }}</label>
                                <div *ngIf="items.length > 0">
                                <div>
                                      <ng-select [allowClear]="true"
                                                  [items]="items"
                                                  (data)="onRefresh($event)"
                                                  (selected)="onSelect($event)"
                                                  (removed)="onRemove($event)"
                                                  (typed)="onType($event)"
                                                  placeholder="{{selected.title}}">
                                      </ng-select>
                               </div>
                            </div>
                         </div>
                     </div> <!-- Company -->`,
    events: [EventService],
    pipes: [TranslatePipe, CapitalizeFirstLetterPipe],
    directives: [SELECT_DIRECTIVES, NgClass, CORE_DIRECTIVES, FORM_DIRECTIVES, BUTTON_DIRECTIVES ]
})
export class SelectEventComponent {

    @Input() selected : Event =  new Event;
    private events: Event[] = [];
    private items: Array<SelectEventItem>=new Array<SelectEventItem>();

    constructor(private _eventService:EventService){

    }

    @Output() eventChanged : EventEmitter<Event>  = new EventEmitter<Event>();

    constructor(private _eventService:EventService) {
        console.log("constructor event type cmp");
    }

    ngOnInit() {
        this._eventService.getAllEvents()
            .subscribe((data) => {
                this.events = data;
                for(let i = 0; i < this.events.length; i++) {
                    this.items.push(<SelectEventItem>{
                        id: this.events[i].id, text: this.events[i].title,
                        id: this.events[i].id,
                        title: this.events[i].title,
                        description: this.events[i].description,
                        start: this.events[i].start,
                        end: this.events[i].end,
                        repeat: this.events[i].repeat,
                        author: this.events[i].author
                    });
                }
                console.log("items: ", this.items);
            });
    }

    onSelect(value:SelectEventItem):void {
        // var id : number = value.id;
        // this._eventService.getEventById(id)
        //     .subscribe((event)=>{
        //         this.selected = event;
        //         console.log("find event ", event)
        //     });
            this.selected.id = value.id;
            this.selected.title = value.text;
            this.selected.description = value.description;
            this.selected.start = value.start;
            this.selected.end = value.end;
            this.selected.repeat = value.repeat;
            this.selected.author = value.author;
        console.log("selected company: ", this.selected.title);
        this.eventChanged.emit(this.selected);
    }

    public onRemove(value:SelectEventItem):void {
        console.log('Removed value is: ', value);
    }

    public onType(value:SelectEventItem):void {
        console.log('New search input: ', value);
    }

    public onRefresh(value:SelectEventItem):void {
        this.selected.id = value.id;
        this.selected.title = value.text;
    }
}

export interface SelectEventItem extends Event, SelectItem{
}