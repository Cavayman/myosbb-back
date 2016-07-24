import {Component, OnInit} from 'angular2/core';
import {RouteConfig, ROUTER_DIRECTIVES} from "angular2/router";
import {LoginMainComponent} from "./login_main.component";
import {LoginRedirectService} from "./services/login_redirect.service";
import {LogoutComponent} from "./logout.component";

@Component({
    selector: 'my-app',
    templateUrl:'templates/nav_main.html',
    directives: [ROUTER_DIRECTIVES],
    styleUrls:[
        'assets/css/bootstrap.css',
        'assets/css/style-responsive.css'],
})
@RouteConfig(
    [
        {path:'/login', name:"Login", component:LoginMainComponent, useAsDefault:true},

    ]
)
export class AppComponent implements OnInit {
    isLoggedIn:boolean;
    statusMessage:string;

    constructor(private _loginRedirectService:LoginRedirectService){}

    ngOnInit():any {
        this.statusMessage = "Увійти";
    }

    getLoginStatusOnRedirect():boolean{
        this.isLoggedIn = this._loginRedirectService.getRedirectStatus();
        console.log('fetch redirect status', this.isLoggedIn);
        if(this.isLoggedIn){
            this.statusMessage = "Вийти";
        }
        return this.isLoggedIn;
    }


}