import {bootstrap} from "@angular/platform-browser-dynamic";
import {HTTP_PROVIDERS} from "@angular/http";
import {ROUTER_DIRECTIVES} from "@angular/router";
import {AppComponent} from "./app/app.component";
import {APP_ROUTER_PROVIDERS} from "./app/app.routes";
import {disableDeprecatedForms, provideForms} from "@angular/forms";

bootstrap(AppComponent,
    [HTTP_PROVIDERS,
        APP_ROUTER_PROVIDERS,
        ROUTER_DIRECTIVES,
        disableDeprecatedForms(),
        provideForms()])
    .catch(err => console.error(err));