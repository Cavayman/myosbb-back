import {RouterConfig} from "@angular/router";
import {UserComponent} from "../user/user.component";
import {UserMainComponent} from "./main/user.main.component";
import {UserApartmentComponent} from "./apartment/user.apartment.component";
import {UserBillComponent} from "./bills/user.bill.component";
import {TicketComponent} from "./ticket/ticket.component";
import {MessageComponent} from "./ticket/single.ticket.component";
import {UserEventComponent} from "./event/user.event.component";
import {UserCalendarComponent} from "./calendar/user.calendar.component";
import {UserAttachmentComponent} from "./attachment/user.attachment.component";
import {UserReportComponent} from "./report/user.report.component";
import {UsersComponent} from "./users/users.component";
import {OsbbComponent} from "./osbb/osbb.component";
import {RoleComponent} from "./role/role.component";
import {ProviderComponent} from "./provider/provider.component";
import {ContractComponent} from "./contract/contract.component";
import {ApartmentProfileComponent} from "./ApartmentProfile/apartment.profile";
import {ProviderInfoComponent} from "./provider/provider-info";
import {ProfileComponent} from "./profile/profile.component";
import {ProviderUserPageComponent} from "./provider/provider_home/provider-user-page.component";
import {OsbbBillComponent} from "./bills/osbb/osbb.bill.component";

export const userRoutes:RouterConfig = [
    {
        path: 'home/user',
        children: [
            {path: 'main', component: ProfileComponent},
            {path: '', redirectTo: 'main', pathMatch: 'full'},
            {path: 'apartment', component: UserApartmentComponent},
            {path: 'event', component: UserEventComponent},
            {path: 'calendar', component: UserCalendarComponent},
            {path: 'attachment', component: UserAttachmentComponent},
            {path: 'bill', component: UserBillComponent},
            {path: 'osbb/bill', component: OsbbBillComponent},
            {path: 'ticket', component: TicketComponent},
            {path: 'ticket/:id', component: MessageComponent},
            {path: 'report', component: UserReportComponent},
            {path: 'users', component: UsersComponent},
            {path: 'profile', component: ProfileComponent},
            {path: 'osbb', component: OsbbComponent},
            {path: 'role', component: RoleComponent},
            {path: 'provider', component: ProviderComponent},
            {path: 'provider/info/:id', component:ProviderInfoComponent},
            {path: 'provider/info', component:ProviderUserPageComponent},
            {path: 'contract', component: ContractComponent},
            {path:'apartment/apartmentprofile/:id', component:ApartmentProfileComponent}
        ],
        component: UserComponent,
    }
];