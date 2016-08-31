import {Component} from "@angular/core";
import {ROUTER_DIRECTIVES, Router} from "@angular/router";

@Component({
    selector: 'my-redirect',
    templateUrl: 'src/app/user/report/download/redirect.html',
    directives: [ROUTER_DIRECTIVES]
})
export class RedirectComponent {

    constructor(private _router:Router) {

    }

    reroute() {
        this._router.navigate(['home/user/report']);
    }

}