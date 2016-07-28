import { provideRouter, RouterConfig } from '@angular/router';
import {UserComponent} from "../user/user.component";
import {HomeComponent} from "./home.component";
export const homeRoutes:RouterConfig = [
    {
        path: 'home/user',
        component: UserComponent

    }
];