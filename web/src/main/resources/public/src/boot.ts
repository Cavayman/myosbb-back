import {bootstrap} from '@angular/platform-browser-dynamic';
import {ROUTER_DIRECTIVES} from '@angular/router';
import {AppComponent} from "./app/app.component";
import {APP_ROUTER_PROVIDERS} from "./app/app.routes";
import {disableDeprecatedForms, provideForms} from '@angular/forms';
import {HTTP_PROVIDERS, Http} from '@angular/http'
import {AppComponent} from "./app/app.component";
import {TranslateService, TranslateLoader, TranslateStaticLoader} from "ng2-translate/ng2-translate";

bootstrap(AppComponent,
    [HTTP_PROVIDERS,
        APP_ROUTER_PROVIDERS,
        ROUTER_DIRECTIVES,
        disableDeprecatedForms(),
        provideForms(),
        // not required, but recommended to have 1 unique instance of your service
        {
            provide: TranslateLoader,
            useFactory: (http: Http) => new TranslateStaticLoader(http, 'assets/i18n', '.json'),
            deps: [Http]
        },

        // use TranslateService here, and not TRANSLATE_PROVIDERS (which will define a default TranslateStaticLoader)
        TranslateService])
    .catch(err => console.error(err));