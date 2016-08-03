import {Component, OnInit, ViewContainerRef} from "@angular/core";
import {ROUTER_DIRECTIVES} from "@angular/router";
import {HeaderComponent} from "./header/header.component";
import {LoginStat} from "../shared/services/login.stats";

@Component({
    selector: 'my-app',
    templateUrl: 'src/app/app.html',
    directives: [ROUTER_DIRECTIVES, HeaderComponent],
    providers: [LoginStat],
})
export class AppComponent implements OnInit {

    isLoggedIn: boolean;

    constructor(private _loginStat: LoginStat, private viewContainerRef: ViewContainerRef) {
    }

    ngOnInit(): any {

    }
}