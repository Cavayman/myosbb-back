import {ChangeDetectorRef, OnInit} from "@angular/core";
import {Component} from "@angular/core";
import {Schedule, Dialog, Button, InputText, Calendar, ToggleButton} from "primeng/primeng";
import {Event} from "../event/event.interface";
import {CalendarService} from "./calendar.service";
import {TranslatePipe, TranslateService} from "ng2-translate/ng2-translate";
import {CapitalizeFirstLetterPipe} from "../../../shared/pipes/capitalize-first-letter";

@Component({
    selector: 'my-calendar',
    templateUrl: 'src/app/user/calendar/calendar.html',
    pipes: [TranslatePipe, CapitalizeFirstLetterPipe],
    providers: [CalendarService],
    directives: [Schedule, Dialog, Button, InputText, Calendar, ToggleButton]
})

export class UserCalendarComponent implements OnInit {

    events: any[];
    header: any;
    event: Event = new Event;
    dialogVisible: boolean = false;
    idGen: number = 100;
    uk: any;
    lang: any;

    constructor(private calendarService: CalendarService, private cd: ChangeDetectorRef,
                private translate: TranslateService) { }

    getLang() {
        let lang = this.lang;
        return lang;
    }

    ngOnInit() {
        this.calendarService.getEvents().then(events => {this.events = events; console.log("component : " + this.events);});

        this.lang = this.translate.currentLang;
        console.log("calendar lang " + this.lang);
        // lang('uk');

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
            end.stripTime();
            this.event.end= end.format();
        }

        this.event.id = e.calEvent.id;
        this.event.start = start.format();
        // this.event.allDay = e.calEvent.allDay;
        this.dialogVisible = true;
    }

    saveEvent() {
        //update
        if(this.event.id) {
            let index: number = this.findEventIndexById(this.event.id);
            if(index >= 0) {
                this.events[index] = this.event;
            }
        }
        //new
        else {
            this.event.id = this.idGen;
            this.events.push(this.event);
            this.event = null;
        }
        this.dialogVisible = false;
    }

    deleteEvent() {
        let index: number = this.findEventIndexById(this.event.id);
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