import { provideRouter, RouterConfig } from '@angular/router';
import {UserComponent} from "../user/user.component";
import {HomeComponent} from "./home.component";
import {HouseShowComponent} from "../house/house.show.component";
export const homeRoutes:RouterConfig = [
    {
        path: 'home/user',
        component: UserComponent

    },
    {path: 'home/house', component: HouseShowComponent},
];