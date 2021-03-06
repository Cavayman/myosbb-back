import {RouterConfig} from "@angular/router";
import {HomeComponent} from "./home.component";
import {ProviderComponent} from "../user/provider/provider.component";
import {OsbbComponent} from "../user/osbb/osbb.component";
import {TicketAdminComponent} from "../user/ticket/ticket.component.admin";
import {UserEventComponent} from "../user/event/user.event.component";
import {HouseTableComponent} from "../house/house.table.component";
import {HouseShowComponent} from "../house/house.show.component";
import {TicketSingleAdminComponent} from "../user/ticket/single.ticket.component.admin";
import {HomeWallComponent} from "./home_wall/home.wall.component";
import {ProviderUserPageWrapperComponent} from "../user/provider/provider_home/provider-user-page.component.wrapper";
import {ProviderInfoPageWrapperComponent} from "../user/provider/provider-info-page-wrapper.component";

export const homeRoutes: RouterConfig = [
    {
        path: 'home',
        component: HomeComponent,

        children: [
            // { path: ':id',  component: UserShowComponent },
            {path: 'wall', component: HomeWallComponent},
            {path: '', redirectTo: 'wall', pathMatch: 'full'},
            {path: 'event', component: UserEventComponent},
            {path: 'ticket', component: TicketAdminComponent},
            {path: 'osbb', component: OsbbComponent},
            {path:'provider/info', component:ProviderUserPageWrapperComponent},
            {path:'provider/info/:id', component:ProviderInfoPageWrapperComponent},
            {path: 'houses', component: HouseTableComponent},
            {path: 'house/:id', component: HouseShowComponent},
            {path: 'ticket/:id', component: TicketSingleAdminComponent},
        ]
    },


]