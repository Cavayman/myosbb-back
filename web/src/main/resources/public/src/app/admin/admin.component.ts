import {Component} from "@angular/core";
import {ROUTER_DIRECTIVES} from "@angular/router";
import {AdminSidebarMenuComponent} from "./sidebar_menu/sidebar_menu.admin.component";
@Component({
    selector: 'my-admin',
    templateUrl: 'src/app/admin/admin.html',
    directives: [ROUTER_DIRECTIVES, AdminSidebarMenuComponent]
})
export class AdminComponent {

}