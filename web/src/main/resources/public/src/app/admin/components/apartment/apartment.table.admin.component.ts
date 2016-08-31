import {Component,onInit} from "@angular/core";
import {UserApartmentComponent} from "../../../../app/user/apartment/user.apartment.component";
@Component(
    {
        selector: 'admin-apartment-table',
        templateUrl: 'src/app/admin/components/apartment/apartment_table.admin.html',
        directives:[UserApartmentComponent]
    }
)
export class ApartmentTableAdminComponent {

    ngOnInit(){
        console.log("privet");
    }
}