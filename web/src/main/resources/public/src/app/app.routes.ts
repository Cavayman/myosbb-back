import {provideRouter, RouterConfig} from "@angular/router";
import {HeaderComponent} from "./header/header.component";
import {LoginComponent} from "./login/login.component";
import {HomeComponent} from "./home/home.component";
import {UserComponent} from "./user/user.component";
import {MessageComponent} from "./user/ticket/message/message.component";
import {homeRoutes} from "./home/home.routes";
import {userRoutes} from "./user/user.routes";
import {RegistrationComponent} from "./registration/registration.component";
import {HouseShowComponent} from "./house/house.show.component";
import {Error404Component} from "../shared/error/error404.component";

export const routes: RouterConfig = [
    {path: 'head/:status', component: HeaderComponent},
    {path: 'login', component: LoginComponent},
    {path: 'registration', component: RegistrationComponent},
    {path: '', redirectTo: 'login', pathMatch: 'full'},
    {path: 'home', component: HomeComponent},
    {path: 'house/:id', component: HouseShowComponent},
    {path: 'message/:id', component: MessageComponent},
    {path: 'home/user', component: UserComponent},
    //{path:'**', component:ErrorHandlerComponent},
    ...homeRoutes,
    ...userRoutes
];

export const APP_ROUTER_PROVIDERS = [
    provideRouter(routes)
];