import {Component, OnInit} from "@angular/core";
import {ROUTER_DIRECTIVES} from "@angular/router";

import {UserComponent} from "../user/user.component";

import {SideBarMenuComponent} from "../home/sidebar_menu/sidebar_menu.component";
import {VoteComponent} from "./voting/vote.component";
import {LoginStat} from "../../shared/services/login.stats";

@Component({
    selector: 'app-home',
    templateUrl: './src/app/home/home.html',
    styleUrls: ['./src/app/home/home.css'],
    directives: [ROUTER_DIRECTIVES, UserComponent, SideBarMenuComponent, VoteComponent],
    inputs: ['isLoggedIn'],
    outputs: ['isLoggedIn'],
    providers: [LoginStat]
})

export class HomeComponent implements OnInit {
  
    isLoggedIn:boolean;

    constructor(private _loginStat:LoginStat) {
        this.isLoggedIn = true;
    }

    ngOnInit():any {
        this._loginStat.loggedInObserver$
            .subscribe(stat => {
                this.isLoggedIn = stat;
            })
    }
}