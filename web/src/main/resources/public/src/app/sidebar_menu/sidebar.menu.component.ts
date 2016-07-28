import {Component} from '@angular/core'
import {ROUTER_DIRECTIVES} from "@angular/router";
@Component({
    selector: 'app-sidebar-menu',
    templateUrl: 'src/app/sidebar_menu/sidebar_menu.html',
    inputs: ['userName'],
    directives: [ROUTER_DIRECTIVES]
})
export class SidebarMenuComponent {

    userName:string;

}