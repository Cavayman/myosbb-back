import {Component,OnInit} from "@angular/core";
import {SidebarMenuComponent} from "../sidebar_menu/sidebar.menu.component";
import {UserMainComponent} from "./main/user.main.component";
import {ROUTER_DIRECTIVES} from "@angular/router";
import {HeaderComponent} from "../header/header.component";
import {CurrentUserService} from "../../shared/services/current.user.service";
@Component({
    selector: 'my-user',
    templateUrl: 'src/app/user/user.html',
    directives: [ROUTER_DIRECTIVES, HeaderComponent, SidebarMenuComponent, UserMainComponent],
    outputs: [],
})
export class UserComponent implements OnInit{
    userName:string;
    _currentUserService:CurrentUserService=null;
    constructor(){
        this._currentUserService=HeaderComponent.currentUserService;
    }

    ngOnInit():any {
        this.userName=this._currentUserService.getUser().firstName+" "+this._currentUserService.getUser().lastName;
        console.log(this.userName);
    }

    
}