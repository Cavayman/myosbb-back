import {RouterConfig} from "@angular/router";
import {HomeComponent} from "./home.component";
import {HouseShowComponent} from "../house/house.show.component";
import {ProviderComponent} from "../user/provider/provider.component";
import {OsbbComponent} from "../user/osbb/osbb.component";
import {TicketComponent} from "../user/ticket/ticket.component";
import {UserEventComponent} from "../user/event/user.event.component";
import {HouseTableComponent} from "../house/house.table.component";
import {UserComponent} from "../user/user.component";
export const homeRoutes:RouterConfig = [
    {
        path: 'home/user',
        component: UserComponent,


    },
    {path: 'home', component: HouseShowComponent,
        children: [
            // { path: ':id',  component: UserShowComponent },
            {path: 'event', component: UserEventComponent},
            {path: 'ticket', component: TicketComponent},
            {path: 'osbb', component: OsbbComponent},
            {path: 'provider', component: ProviderComponent},
            {path: 'houses', component: HouseTableComponent}
        ]
    }

]