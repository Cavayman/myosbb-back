import {RouterConfig} from "@angular/router";
import {UserComponent} from "../user/user.component";
export const homeRoutes:RouterConfig = [
    {
        path: 'home/user',
        component: UserComponent

    }
];