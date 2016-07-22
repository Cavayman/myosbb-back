import {Component} from 'angular2/core';
import {RouteConfig} from "angular2/router";
import {LoginComponent} from "./login.component";
import {ROUTER_DIRECTIVES} from "angular2/router";
import {OnInit} from "angular2/core";
import {LandingPageComponent} from "./landing_main.component";
import {LoginRedirectService} from "./services/login_redirect.service";
import {LogoutComponent} from "./logout.component";

@Component({
    selector: 'my-app',
    templateUrl:'templates/nav_main.html',
    directives: [ROUTER_DIRECTIVES],
    styleUrls:['assets/bower/bootstrap/dist/css/bootstrap.min.css',
                'assets/css/main.min.css'],
})
@RouteConfig(
    [
        {path:'/landing_main', name:"Landing", component:LandingPageComponent, useAsDefault:true},
        {path:'/login', name:'Login', component:LoginComponent},
        {path:'/logout', name:'Logout', component:LogoutComponent}
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