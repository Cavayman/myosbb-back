import {Injectable} from "@angular/core";
import {User} from "../../shared/models/User";
import {Response} from "@angular/http";
import {LoginService} from "../../app/login/login.service";
@Injectable()
export class CurrentUserService {


    private currentUser: User

    constructor(private _loginservice: LoginService) {
        this.currentUser = new User();
        this.getUser();
    }

    setUser(user: Response) {
        this.currentUser = <User>user.json();
    }
    
    setUserPromise(user: User) {
        this.currentUser = user;
        return this.currentUser;
    }

    getUser(): User {
        if ((this.currentUser.firstName.length == 0) && (this._loginservice.checkLogin())) {
            this._loginservice.sendToken().subscribe(
                data => this.setUser(data));
        }
        return this.currentUser;
    }

}