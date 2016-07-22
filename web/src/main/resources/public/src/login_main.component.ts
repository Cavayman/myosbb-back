import {Component} from 'angular2/core'
import {LoginRedirectService} from "./services/login_redirect.service";
import {Router} from "angular2/router";

@Component({
    selector:'login-page-main',
    templateUrl:'templates/login_main.html'
})
export class LoginMainComponent{

    isLoggedIn:boolean;

    constructor(private _router:Router,
                private _loginRedirectService:LoginRedirectService){}


    onUserLoginClick(){
        console.log('navigate back to landing page');
        this.isLoggedIn=true;
        this._loginRedirectService.setLoginStatus(this.isLoggedIn);
        this._router.navigate(['Landing']);
    }


}