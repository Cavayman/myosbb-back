import {Component, OnInit, OnDestroy, Output} from '@angular/core';
import {ROUTER_DIRECTIVES, ActivatedRoute} from '@angular/router';
import 'rxjs/Rx';
import {LoginStat} from "../../shared/services/login.stats";
import {LoginComponent} from "../login/login.component";
import {Http} from "@angular/http";
import {TranslateService, TranslatePipe} from "ng2-translate/ng2-translate";
import {DROPDOWN_DIRECTIVES} from 'ng2-bs-dropdown/dropdown';
import {LoginService} from "../login/login.service";
import {CurrentUserService} from "../../shared/services/current.user.service"

@Component({
    selector: 'app-header',
    templateUrl: 'src/app/header/header.html',
    providers: [LoginStat,LoginService],
    inputs: ['isLoggedIn'],
    directives: [ROUTER_DIRECTIVES, DROPDOWN_DIRECTIVES],
    pipes: [TranslatePipe]

})
export class HeaderComponent implements OnInit,
    OnDestroy {

    isLoggedIn:boolean;
    sub:any;
    static translateService : TranslateService;
    static currentUserService : CurrentUserService;

    languages: Array<string> = ['en', 'uk'];
    selectedLang : string = 'uk';

    constructor(private _currentUserService:CurrentUserService,private _loginService:LoginService,private _loginStat:LoginStat, private _route:ActivatedRoute, private translate: TranslateService, private http:Http) {
        this._loginStat.loggedInObserver$
            .subscribe(stat => {
                this.isLoggedIn = stat;
            });
        HeaderComponent.currentUserService=_currentUserService;
        HeaderComponent.translateService = translate;
        var userLang = navigator.language.split('-')[1]; // use navigator lang if available
        userLang = /(en|uk)/gi.test(userLang) ? userLang : 'uk';

        // this language will be used as a fallback when a translation isn't found in the current language
        translate.setDefaultLang('uk');

        // the lang to use, if the lang isn't available, it will use the current loader to get them
        this.selectedLang = userLang;
        translate.use(this.selectedLang);
        translate.currentLang = this.selectedLang;
        console.log("default lang: ", translate.currentLang);
        console.log("shared sevice: ", HeaderComponent.translateService);
            console.log("shared sevice: ", HeaderComponent.currentUserService);
    
    }


    ngOnInit():any {
        this.sub = this._route.params.subscribe(params=>
            this.isLoggedIn = params['status']);

    }


    ngOnDestroy():any {
        this.sub.unsubscribe();
    }

    onSelect(lang){
        this.selectedLang = lang;
        this.translate.use(lang);
        this.translate.currentLang = lang;
        console.log("current lang: ", this.translate.currentLang);
    }

}