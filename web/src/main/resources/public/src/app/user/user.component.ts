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
    _currentUser=HeaderComponent.currentUserService;
    constructor(){
        console.log("From user component:"+this._currentUser.getUser());
        this.userName=this._currentUser.getUser().firstName;
        console.log(this.userName);
    }

    ngOnInit():any {
        this.userName=this._currentUser.getUser().firstName+" "+this._currentUser.getUser().lastName;
        console.log(this.userName);
    }

    
}