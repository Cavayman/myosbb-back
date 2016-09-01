import {ChangeDetectorRef, OnInit} from "@angular/core";
import {Component} from "@angular/core";
import {Schedule, Dialog, Button, InputText, Calendar, ToggleButton} from "primeng/primeng";
import {Event} from "../event/event.interface";
import {TranslatePipe, TranslateService} from "ng2-translate/ng2-translate";
import {CapitalizeFirstLetterPipe} from "../../../shared/pipes/capitalize-first-letter";
import {EventService} from "../event/event.service";
import {routes} from "../../app.routes";

@Component({
    selector: 'my-calendar',
    templateUrl: 'src/app/user/calendar/calendar.html',
    pipes: [TranslatePipe, CapitalizeFirstLetterPipe],
    providers: [EventService],
    directives: [Schedule, Dialog, Button, InputText, Calendar, ToggleButton]
})

export class UserCalendarComponent implements OnInit {

    events:Event[];
    header: any;
    event:Event = new Event;
    dialogVisible: boolean = false;
    idGen: number = 100;
    uk: any;
    lang: string = "uk";

    constructor(private eventService: EventService, private cd: ChangeDetectorRef,
                private translate: TranslateService) { }

    getLang() {
        let lang = this.translate.currentLang;
        if (this.lang != lang) this.ngOnInit();
        return this[lang];
    }

    ngOnInit() {
        this.eventService.getEvents().then(events => {this.events = events; console.log("component : " + this.events);});

        this.lang = this.translate.currentLang;
        console.log("calendar lang " + this.lang);

        this.uk = {
            monthNames: ['Січень', 'Лютий', 'Березень', 'Квітень', 'Травень', 'Червень', 'Липень',
                'Серпень', 'Вересень', 'Жовтень', 'Листопад', 'Грудень'],
            monthNamesShort: ['Січ', 'Лют', 'Бер', 'Квіт', 'Трав', 'Черв',
                'Лип', 'Серп', 'Верес', 'Жовт', 'Лист', 'Груд'],
            dayNames: ['Неділя', 'Понеділок', 'Вівторорк', 'Середа',
                'Четвер', 'Пятниця', 'Субота'],
            dayNamesShort: ['Нд', 'Пн', 'Вт', 'Ср', 'Чт', 'Пт', 'Сб'],
            buttonText: {
                today:    'сьогодні',
                month:    'місяць',
                week:     'тиждень',
                day:      'день'
            },
            allDayText: 'Весь день'
        };

        this.header = {
            left: 'prev,next today',
            center: 'title',
            right: 'month,agendaWeek,agendaDay'
        };
    }

    handleDayClick(event) {
        this.event = new Event();
        this.event.start = event.date.format();
        this.dialogVisible = true;
        //trigger detection manually as somehow only moving the mouse quickly after click triggers the automatic detection
        this.cd.detectChanges();
    }

    handleEventClick(e) {
        this.event = new Event();
        this.event.title = e.calEvent.title;

        let start = e.calEvent.start;
        let end = e.calEvent.end;
        if(e.view.title === 'month') {
            start.stripTime();
        }

        if(end) {
            // end.stripTime();
            this.event.end= end.format();
        }

        this.event.id = e.calEvent.id;
        this.event.start = start.format();
        this.event.end = end.format();
        this.dialogVisible = true;
    }

    saveEvent(event:Event) {
        //update
        if(this.event.id) {
            this.eventService.editAndSave(event);
            let index: number = this.findEventIndexById(this.event.id);
            if(index >= 0) {
                this.event = event;
                this.events[index] = this.event;
                this.eventService.editAndSave(this.event);
            }
        }
        //new
        else {
            this.event.id = this.idGen;
            this.events.push(this.event);
            this.eventService.addEvent(this.event);
            this.event = null;
        }
        console.log("!!! " + this.event);
        this.dialogVisible = false;
    }

    deleteEvent() {
        let index: number = this.findEventIndexById(this.event.id);
        this.eventService.deleteEventById(this.event.id);
        if(index >= 0) {
            this.events.splice(index, 1);
        }
        this.dialogVisible = false;
    }

    findEventIndexById(id: number) {
        let index = -1;
        for(let i = 0; i < this.events.length; i++) {
            if(id == this.events[i].id) {
                index = i;
                break;
            }
        }
        return index;
    }
}