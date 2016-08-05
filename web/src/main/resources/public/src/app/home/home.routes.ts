import { provideRouter, RouterConfig } from '@angular/router';
import {UserComponent} from "../user/user.component";
import {HomeComponent} from "./home.component";
import {HouseShowComponent} from "../house/house.show.component";
import {ProviderComponent} from "../user/provider/provider.component";
import {OsbbComponent} from "../user/osbb/osbb.component";
import {TicketComponent} from "../user/ticket/ticket.component";
import {UserEventComponent} from "../user/event/user.event.component";
export const homeRoutes:RouterConfig = [
    {
        path: 'home/user',
        component: UserComponent,


    },
    {path: 'home/house', component: HouseShowComponent,
        children: [
            // { path: ':id',  component: UserShowComponent },
            {path: 'event', component: UserEventComponent},
            {path: 'ticket', component: TicketComponent},
            {path: 'osbb', component: OsbbComponent},
            {path: 'provider', component: ProviderComponent}
        ]
    },
];