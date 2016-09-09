import {Component} from "@angular/core";
import {HouseTableComponent} from "../../../house/house.table.component";
import {ROUTER_DIRECTIVES} from "@angular/router";
@Component(
    {
        selector: 'admin-house-table',
        templateUrl: 'src/app/admin/components/house/house_table.admin.html',
        directives: [HouseTableComponent, ROUTER_DIRECTIVES]
    }
)
export class HouseTableAdminComponent {

}