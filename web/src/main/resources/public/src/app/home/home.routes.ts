import {RouterConfig} from "@angular/router";
import {HomeComponent} from "./home.component";
import {ProviderComponent} from "../user/provider/provider.component";
import {OsbbComponent} from "../user/osbb/osbb.component";
import {TicketComponent} from "../user/ticket/ticket.component";
import {UserEventComponent} from "../user/event/user.event.component";
import {HouseTableComponent} from "../house/house.table.component";
export const homeRoutes: RouterConfig = [
    {
        path: 'home',
        component: HomeComponent,

        children: [
            // { path: ':id',  component: UserShowComponent },
            {path: 'event', component: UserEventComponent},
            {path: 'ticket', component: TicketComponent},
            {path: 'osbb', component: OsbbComponent},
            {path: 'provider', component: ProviderComponent},
            {path: 'houses', component: HouseTableComponent}
        ]
    },


]