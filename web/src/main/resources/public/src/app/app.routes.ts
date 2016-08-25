import {provideRouter, RouterConfig} from "@angular/router";
import {HeaderComponent} from "./header/header.component";
import {LoginComponent} from "./login/login.component";
import {HomeComponent} from "./home/home.component";
import {UserComponent} from "./user/user.component";
import {MessageComponent} from "./user/ticket/message/message.component";
import {homeRoutes} from "./home/home.routes";
import {userRoutes} from "./user/user.routes";
import {RegistrationComponent} from "./registration/registration.component";
import {Error404Component} from "../shared/error/error404.component";
import {ErrorHandlerComponent} from "../shared/error/error.handler.component";
import {adminRoutes} from "./admin/admin.routes";

export const routes: RouterConfig = [
    {path: 'head/:status', component: HeaderComponent},
    {path: 'login', component: LoginComponent},
    {path: 'registration', component: RegistrationComponent},
    {path: '', redirectTo: 'login', pathMatch: 'full'},
    {path: 'home', component: HomeComponent},
    {path: 'message/:id', component: MessageComponent},
    {path: 'home/user', component: UserComponent},
    ...homeRoutes,
    ...userRoutes,
    ...adminRoutes,
    {path: '**', component: ErrorHandlerComponent},
];

export const APP_ROUTER_PROVIDERS = [
    provideRouter(routes)
];