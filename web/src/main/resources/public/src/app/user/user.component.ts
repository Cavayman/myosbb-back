import {Component} from "@angular/core";
import {SidebarMenuComponent} from "../sidebar_menu/sidebar.menu.component";
import {UserMainComponent} from "./main/user.main.component";
import {ROUTER_DIRECTIVES} from "@angular/router";
import {UserHouseComponent} from "./house/user.house.component";

@Component({
    selector: 'my-user',
    templateUrl: 'src/app/user/user.html',
    directives: [ROUTER_DIRECTIVES, SidebarMenuComponent, UserMainComponent, UserHouseComponent],
    outputs: ['userName']
})
export class UserComponent {

    userName:string = 'Barack Obama';


}