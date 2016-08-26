import {Component, OnInit} from "@angular/core";
import {LoginService} from "./login.service";
import {Router} from "@angular/router";
import {RegistrationComponent} from "../registration/registration.component";
import {CurrentUserService} from "../../shared/services/current.user.service";

@Component({
    selector: 'app-login',
    templateUrl: 'src/app/login/login.html',
    directives: [RegistrationComponent],
    providers: [LoginService],
    outputs: ['isLoggedIn']
})
export class LoginComponent implements OnInit {
    ngOnInit(): any {
        return undefined;
    }
    private model = { 'username': '', 'password': '' };
    private currentUserName = '';
    private isLoggedIn: boolean = false;
    private logInError:boolean=false;
    // isLoggedIn:boolean;
    // @Output() private loggedIn = new EventEmitter();

    constructor(private _router: Router, private loginService: LoginService
        , private _currentUserService: CurrentUserService) {
    }

    onSubmit() {
        this.isLoggedIn = false;
        this.loginService.sendCredentials(this.model).subscribe(
            data => {
                localStorage.setItem("id_token", JSON.parse(JSON.stringify(data))._body);
                this.loginService.sendToken().subscribe(
                    data => {
                        if (!this.isLoggedIn) {
                            this.currentUserName = this.model.username;
                            localStorage.setItem("currentUserName", this.model.username);
                            this.model.username = "";
                            this.model.password = "";
                            this.isLoggedIn = true;
                            this._currentUserService.setUser(data);
                            this._router.navigate(['home/wall']);
                        }
                    }
                )
            }, 
            err =>{
            this.model.password = "";
            this.logInError=true;
        },
        () => console.log('Sending credentials completed')
        )
        

    }

    onUserRegistrationClick() {
        this._router.navigate(['registration']);

    }

    // onUserLoginClick() {
    //     this.isLoggedIn = true;
    //     this.loggedIn.emit(this.isLoggedIn);
    //     this._loginStat.setLoginStat(this.isLoggedIn);
    //     this._router.navigate(['home']);
    //
    // }

}