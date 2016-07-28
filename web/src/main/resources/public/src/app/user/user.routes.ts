import { provideRouter, RouterConfig } from '@angular/router';
import {UserComponent} from "../user/user.component";
import {HomeComponent} from "./home.component";
import {UserMainComponent} from "./main/user.main.component";
export const userRoutes:RouterConfig = [
    {
        path: 'home/user',
        component: UserComponent,

        children: [
            // { path: ':id',  component: UserShowComponent },
            {path: 'main', component: UserMainComponent},
            {path: '', redirectTo: 'main', pathMatch: 'full'},
        ]

    }
];