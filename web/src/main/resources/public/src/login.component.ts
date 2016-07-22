import {Component, OnInit} from "angular2/core";
import {Router, ROUTER_DIRECTIVES} from "angular2/router";
import {LoginRedirectService} from "./services/login_redirect.service";

@Component(
    {
        selector:'my-login',
        templateUrl:'templates/login_main.html',
        styleUrls:['assets/css/style.css',
            'assets/css/style-responsive.css',
            'assets/css/bootstrap.css',
            'assets/font-awesome/css/font-awesome.css'],
        directives: [ROUTER_DIRECTIVES],
    }
)
export class LoginComponent implements OnInit{

    isLoggedIn:boolean;

    constructor(private _router:Router, private _loginRedirectService:LoginRedirectService){}

    ngOnInit():any {

    }

    onUserLoginClick(){
        console.log('navigate back to landing page');
        this.isLoggedIn=true;
        this._loginRedirectService.setLoginStatus(this.isLoggedIn);
        this._router.navigate(['Landing']);
    }

}