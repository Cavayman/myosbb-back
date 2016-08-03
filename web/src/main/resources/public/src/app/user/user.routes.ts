import {RouterConfig} from "@angular/router";
import {UserComponent} from "../user/user.component";
import {UserMainComponent} from "./main/user.main.component";
import {UserHouseComponent} from "./house/user.house.component";
import {UserApartmentComponent} from "./apartment/user.apartment.component";
import {UserBillComponent} from "./bills/user.bill.component";
import {UserMessageComponent} from "./message/user.message.component";
import {UserEventComponent} from "./event/user.event.component";
import {UserAttachmentComponent} from "./attachment/user.attachment.component";
import {UserReportComponent} from "./report/user.report.component";
import {UsersComponent} from "./users/users.component";
import {OsbbComponent} from "./osbb/osbb.component";
import {RoleComponent} from "./role/role.component";

export const userRoutes:RouterConfig = [
    {
        path: 'home/user',
        component: UserComponent,

        children: [
            // { path: ':id',  component: UserShowComponent },
            {path: 'main', component: UserMainComponent},
            {path: '', redirectTo: 'main', pathMatch: 'full'},
            {path: 'house', component: UserHouseComponent},
            {path: 'apartment', component: UserApartmentComponent},
            {path: 'event', component: UserEventComponent},
            {path: 'attachment', component: UserAttachmentComponent},
            {path: 'bill', component: UserBillComponent},
            {path: 'message', component: UserMessageComponent},
            {path: 'report', component: UserReportComponent},
            {path: 'users', component: UsersComponent},
            {path: 'osbb', component: OsbbComponent},
            {path: 'role', component: RoleComponent}
        ]

    }
];