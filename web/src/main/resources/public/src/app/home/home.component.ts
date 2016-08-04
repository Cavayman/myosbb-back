import {Component, OnInit} from "@angular/core";
import {ROUTER_DIRECTIVES} from "@angular/router";
import {UserComponent} from "../user/user.component";
import {HeaderComponent} from "../home/header/header.component";
import {LoginStat} from "../../shared/services/login.stats";
import {SideBarMenuComponent} from "./sidebar_menu/sidebar_menu.component";

@Component({
    selector: 'app-home',
    templateUrl: 'src/app/home/home.html',
    directives: [ROUTER_DIRECTIVES, UserComponent, HeaderComponent, SideBarMenuComponent],
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