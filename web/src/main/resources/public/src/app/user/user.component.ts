import {Component} from "@angular/core";
import {SidebarMenuComponent} from "../sidebar_menu/sidebar.menu.component";
import {UserMainComponent} from "./main/user.main.component";
import {ROUTER_DIRECTIVES} from "@angular/router";
import {HeaderComponent} from "../header/header.component";

@Component({
    selector: 'my-user',
    templateUrl: 'src/app/user/user.html',
    directives: [ROUTER_DIRECTIVES, HeaderComponent, SidebarMenuComponent, UserMainComponent],
    outputs: ['userName']
})
export class UserComponent {

    userName:string = 'Barack Obama';


}