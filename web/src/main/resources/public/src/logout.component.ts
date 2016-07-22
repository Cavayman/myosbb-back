import {Component} from 'angular2/core'
import {OnInit} from "angular2/core";
import {ROUTER_DIRECTIVES} from "angular2/router";
import {Router} from "angular2/router";
import {LoginRedirectService} from "./services/login_redirect.service";

@Component({
    selector:'my-logout',
    template:'',
    directives: [ROUTER_DIRECTIVES]
})
export class LogoutComponent implements OnInit{

    constructor(private router:Router, private _loginRedirect:LoginRedirectService){}
    ngOnInit():any {
        this._loginRedirect.setLoginStatus(false);
        this.router.navigateByUrl('/');
    }
}