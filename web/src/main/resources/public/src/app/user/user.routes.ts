import {RouterConfig} from "@angular/router";
import {UserComponent} from "../user/user.component";
import {UserMainComponent} from "./main/user.main.component";
import {UserApartmentComponent} from "./apartment/user.apartment.component";
import {UserBillComponent} from "./bills/user.bill.component";
import {TicketComponent} from "./ticket/ticket.component";
//import {UserMessageComponent} from "./message/message.component";
import {UserEventComponent} from "./event/user.event.component";
import {UserAttachmentComponent} from "./attachment/user.attachment.component";
import {UserReportComponent} from "./report/user.report.component";
import {UsersComponent} from "./users/users.component";
import {OsbbComponent} from "./osbb/osbb.component";
import {RoleComponent} from "./role/role.component";
import {ProviderComponent} from "./provider/provider.component";
import {ContractComponent} from "./contract/contract.component";
import{ApartmentProfileComponent} from "./ApartmentProfile/apartment.profile";
import {ProviderInfoComponent} from "./provider/provider-info";


export const userRoutes:RouterConfig = [
    {
        path: 'home/user',
        children: [
            // { path: ':id',  component: UserShowComponent },
            {path: 'main', component: UserMainComponent},
            {path: '', redirectTo: 'main', pathMatch: 'full'},
            {path: 'apartment', component: UserApartmentComponent},
            {path: 'event', component: UserEventComponent},
            {path: 'attachment', component: UserAttachmentComponent},
            {path: 'bill', component: UserBillComponent},
            {path: 'ticket', component: TicketComponent},
            // {path: 'message', component: UserMessageComponent},
            {path: 'report', component: UserReportComponent},
            {path: 'users', component: UsersComponent},
            {path: 'osbb', component: OsbbComponent},
            {path: 'role', component: RoleComponent},
            {path: 'provider', component: ProviderComponent},
            {path:'provider/info/:id', component:ProviderInfoComponent},
            {path: 'contract', component: ContractComponent},
            {path:'apartment/apartmentprofile/:id', component:ApartmentProfileComponent}
        ],
        component: UserComponent,
    }
];