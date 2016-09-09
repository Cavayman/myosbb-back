import {Component} from "@angular/core";
import {HouseShowComponent} from "../../../house/house.show.component";
@Component(
    {
        selector: 'admin-house-show',
        templateUrl: 'src/app/admin/components/house/house_show.admin.html',
        directives:[HouseShowComponent]
    }
)
export class HouseShowAdminComponent {

}