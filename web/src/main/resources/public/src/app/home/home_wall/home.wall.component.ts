import {Component, OnInit} from "@angular/core";
import {ROUTER_DIRECTIVES} from "@angular/router";

import {VoteComponent} from "../voting/vote.component";

import {IOsbb, Osbb} from "../../../shared/models/osbb";
import { OsbbService } from '../../user/osbb/osbb.service';
import { UserCalendarComponent } from '../../user/calendar/user.calendar.component';

@Component({
    selector: 'home-wall',
    templateUrl: './src/app/home/home_wall/home.wall.html',
    styleUrls: ['./src/app/home/home_wall/home.wall.css'],
    providers: [OsbbService],
    directives: [ROUTER_DIRECTIVES, VoteComponent, UserCalendarComponent]
})

export class HomeWallComponent implements OnInit {

    isLoggedIn:boolean;
    currentOsbbId: number = 2;
    currentOsbb: Osbb;

    constructor(private osbbService: OsbbService) {
        this.currentOsbb = new Osbb('', '', '', '','');
    }

    ngOnInit():any {
         this.osbbService.getOsbbById(this.currentOsbbId).then(osbb => this.currentOsbb = osbb);
    }

    printHello(): void {             //DELETE ME
        //console.log("Привіт");
        console.log("CurrentOsbb: "  + this.currentOsbb.logoUrl);
    }   
}