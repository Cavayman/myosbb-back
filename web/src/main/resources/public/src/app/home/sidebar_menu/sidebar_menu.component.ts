import {Component} from "@angular/core";
import {ROUTER_DIRECTIVES} from "@angular/router";
@Component({
    selector: 'osbb-sidebar-menu',
    templateUrl: './src/app/home/sidebar_menu/sidebar_menu.html',
    // inputs: ['userName'],
    directives: [ROUTER_DIRECTIVES]
})
export class SideBarMenuComponent {

    userName:string;

}