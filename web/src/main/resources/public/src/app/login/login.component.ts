import {Component, OnInit, Output, EventEmitter} from "@angular/core";
import {Router} from "@angular/router";
import {LoginStat} from "../../shared/services/login.stats";
import {HeaderComponent} from "../header/header.component";

@Component({
    selector: 'app-login',
    templateUrl: 'src/app/login/login.html',
    providers: [LoginStat],
    directives: [HeaderComponent],
    outputs: ['isLoggedIn']
})
export class LoginComponent implements OnInit {

    isLoggedIn:boolean;
    @Output() private loggedIn = new EventEmitter();

    constructor(private _router:Router, private _loginStat:LoginStat) {
    }

    ngOnInit():any {

    }

    onUserLoginClick() {
        this.isLoggedIn = true;
        this.loggedIn.emit(this.isLoggedIn);
        this._loginStat.setLoginStat(this.isLoggedIn);
        this._router.navigate(['home']);

    }

}