import {Component} from "@angular/core";
import {ROUTER_DIRECTIVES} from "@angular/router";
import {CapitalizeFirstLetterPipe} from "../../../shared/pipes/capitalize-first-letter";
import {TranslatePipe} from "ng2-translate/ng2-translate";

@Component({
    selector: 'osbb-sidebar-menu',
    templateUrl: './src/app/home/sidebar_menu/sidebar_menu.html',
    // inputs: ['userName'],
    directives: [ROUTER_DIRECTIVES],
    pipes: [TranslatePipe, CapitalizeFirstLetterPipe]
})
export class SideBarMenuComponent {

    userName:string;

}