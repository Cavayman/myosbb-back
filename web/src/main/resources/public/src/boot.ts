import {bootstrap} from '@angular/platform-browser-dynamic';
import {HTTP_PROVIDERS, Http} from '@angular/http'
import {ROUTER_DIRECTIVES} from '@angular/router';
import {APP_ROUTER_PROVIDERS} from "./app/app.routes";
import {AppComponent} from "./app/app.component";
import {disableDeprecatedForms, provideForms} from '@angular/forms';
import {TranslateService, TranslateLoader, TranslateStaticLoader} from "ng2-translate/ng2-translate";
import {CurrentUserService} from "./shared/services/current.user.service";

bootstrap(AppComponent,
    [HTTP_PROVIDERS,
        APP_ROUTER_PROVIDERS,
        ROUTER_DIRECTIVES,CurrentUserService,
        TranslateService,
        {
        provide: TranslateLoader,
        useFactory: (http: Http) => new TranslateStaticLoader(http, 'assets/i18n', '.json'),
        deps: [Http]
        },

        disableDeprecatedForms(),
        provideForms()])
    .catch(err => console.error(err));