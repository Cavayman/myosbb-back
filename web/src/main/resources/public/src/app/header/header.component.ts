import {Component, OnInit, OnDestroy} from "@angular/core";
import {ROUTER_DIRECTIVES, ActivatedRoute} from "@angular/router";
import "rxjs/Rx";
import {LoginStat} from "../../shared/services/login.stats";

@Component({
    selector: 'app-header',
    templateUrl: 'src/app/header/header.html',
    providers: [LoginStat],
    inputs: ['isLoggedIn'],
    directives: [ROUTER_DIRECTIVES]
})
export class HeaderComponent implements OnInit, OnDestroy {

    isLoggedIn:boolean;
    sub:any;

    constructor(private _loginStat:LoginStat, private _route:ActivatedRoute) {
        this._loginStat.loggedInObserver$
            .subscribe(stat => {
                this.isLoggedIn = stat;
            })
    }


    ngOnInit():any {
        this.sub = this._route.params.subscribe(params=>
            this.isLoggedIn = params['status']);

    }


    ngOnDestroy():any {
        this.sub.unsubscribe();
    }
}