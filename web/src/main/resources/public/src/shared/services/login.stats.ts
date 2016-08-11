import {Injectable} from "@angular/core";
import {Subject} from "rxjs/Subject";

@Injectable()
export class LoginStat {

    private isLoggedIn = new Subject<boolean>();
    loggedInObserver$ = this.isLoggedIn.asObservable();
    setLoginStat(stat:boolean) {
        this.isLoggedIn.next(stat);
    }


}