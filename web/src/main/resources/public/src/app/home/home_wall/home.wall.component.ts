import {Component, OnInit} from "@angular/core";
import {ROUTER_DIRECTIVES} from "@angular/router";

import {VoteComponent} from "../voting/vote.component";

import {IOsbb, Osbb} from "../../../shared/models/osbb";
import { OsbbService } from '../../user/osbb/osbb.service';
import { UserCalendarComponent } from '../../user/calendar/user.calendar.component';
import {TranslatePipe} from "ng2-translate";
import {CapitalizeFirstLetterPipe} from "../../../shared/pipes/capitalize-first-letter";

@Component({
    selector: 'home-wall',
    templateUrl: './src/app/home/home_wall/home.wall.html',
    styleUrls: ['./src/app/home/home_wall/home.wall.css'],
    providers: [OsbbService],
    directives: [ROUTER_DIRECTIVES, VoteComponent, UserCalendarComponent],
    pipes:[CapitalizeFirstLetterPipe, TranslatePipe]
})
export class HomeWallComponent implements OnInit {

    isLoggedIn:boolean;
    currentOsbbId: number = 7;
    currentOsbb: Osbb;

    constructor(private osbbService: OsbbService) {
        this.currentOsbb = new Osbb();
    }

    ngOnInit():any {
         this.osbbService.getOsbbById(this.currentOsbbId).then(osbb => {
                if(osbb !== undefined) {
                    this.currentOsbb = osbb;
                } else {
                    let o = new Osbb();
                    o.name="";
                    o.description="";
                    o.address="";
                    o.logoUrl="";
                    o.district="";
                    this.currentOsbb = o;
                }
            });
    }
}