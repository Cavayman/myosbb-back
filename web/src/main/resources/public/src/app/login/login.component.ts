import {Component, OnInit} from "@angular/core";
import {LoginService} from "./login.service";
import {Router} from "@angular/router";
import {RegistrationComponent} from "../registration/registration.component";
import {CurrentUserService} from "../../shared/services/current.user.service";
import {ToasterContainerComponent, ToasterService, ToasterConfig} from 'angular2-toaster/angular2-toaster';
import MaskedInput from 'angular2-text-mask';
import emailMask from 'node_modules/text-mask-addons/dist/emailMask.js'

@Component({
    selector: 'app-login',
    templateUrl: 'src/app/login/login.html',
    styleUrls: ['assets/css/login/login.css'],
    directives: [RegistrationComponent, ToasterContainerComponent, MaskedInput],
    providers: [LoginService, ToasterService],
    outputs: ['isLoggedIn']
})
export class LoginComponent implements OnInit {

    ngOnInit():any {
        this.isLoggedIn=this.loginService.checkLogin();
    }

    public emailMask = emailMask;

    private model = {"username": "", "password": ""};
    private isLoggedIn:boolean = this.loginService.checkLogin();
    private logInError:boolean = false;
    public toasterconfig:ToasterConfig = new ToasterConfig({showCloseButton: true, tapToDismiss: true, timeout: 5000});
    forgotEmail = "";

    constructor(private _router:Router, private loginService:LoginService
        , private _currentUserService:CurrentUserService, private _toasterService:ToasterService) {
    }

    onSubmit() {
        this.loginService.sendCredentials(this.model).subscribe(
            data => {
                if (!this.loginService.checkLogin()) {
                    this.tokenParseInLocalStorage(data.json());
                    this.loginService.sendToken().subscribe(
                        data=> {
                            this._currentUserService.setUser(data);
                            this.model.username = "";
                            this.model.password = "";
                            this.isLoggedIn = true;
                            this._toasterService.pop('success'
                                , "Congratulation," + this._currentUserService.getUser().firstName + " !"
                                , 'We glad to see you hare again');
                            this._router.navigate(['home/wall']);
                        }
                    )
                }
            },
            err => {
                this.model.password = "";
                this.handleErrors(err);
            },
            () => console.log('Sending credentials completed')
        )
    }

    private handleErrors(error) {
        this._toasterService.pop('error', "Try again...later", "Something vary bad happened.");
        return;
    }

    tokenParseInLocalStorage(data:any) {
        localStorage.setItem("access_token", data.access_token);
        localStorage.setItem("token_type", data.token_type);
        localStorage.setItem("expires_in",new Date().setSeconds(data.expires_in));
        localStorage.setItem("scope", data.scope);
        localStorage.setItem("jti", data.jti);
        localStorage.setItem("refresh_token", data.refresh_token);
    }

    onUserRegistrationClick() {
        this._router.navigate(['registration']);

    }

    emailValid:boolean = false;

    validateEmail() {
        this.loginService.validateEmail(this.forgotEmail.replace(/ /g, '')).subscribe(
            data=> {
                if (data.json() === "FOUND") {
                    this.emailValid = true;
                } else {
                    this.emailValid = false;
                }
            }
        )
    }

    sendEmail() {
        if (this.emailValid) {
            this.loginService.sendPassword(this.forgotEmail).subscribe(
                data=>{
                    this._toasterService.pop('success'
                        , "Congratulation!"
                        , 'Password was sendet on your email.Check it and get back soon.');

                },
                err => {
                    this.forgotEmail = "";
                    this.handleErrors(err);
                }
            )}
    }
}