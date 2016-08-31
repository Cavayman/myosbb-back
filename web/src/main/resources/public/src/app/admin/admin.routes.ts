import {AdminComponent} from "./admin.component";
import {RouterConfig} from "@angular/router";
import {OsbbTableAdminComponent} from "./components/osbb/osbb_table.admin.component";
import {HouseTableAdminComponent} from "./components/house/house_table.admin.component";
import {ApartmentTableAdminComponent} from "./components/apartment/apartment.table.admin.component";
import {UserTableAdminComponent} from "./components/user/user_table.admin.component";
import {HouseShowAdminComponent} from "./components/house/house_show.admin.component";
export const adminRoutes: RouterConfig = [
    {
        path: 'admin',
        component: AdminComponent,

        children: [
            {path: 'osbb', component: OsbbTableAdminComponent},
            {path: '', redirectTo: 'osbb', pathMatch: 'full'},
            {path: 'houses', component: HouseTableAdminComponent},
            {path: 'house/:id', component: HouseShowAdminComponent},
            {path: 'apartments', component: ApartmentTableAdminComponent},
            {path: 'users', component: UserTableAdminComponent},
        ]
    },


];