import {Component, OnInit} from '@angular/core'
import {ROUTER_DIRECTIVES} from '@angular/router'
import {UserComponent} from "../user/user.component";

@Component({
    selector: 'app-home',
    templateUrl: 'src/app/home/home.html',
    directives: [ROUTER_DIRECTIVES, UserComponent],
    inputs: ['isLoggedIn'],
    outputs: ['isLoggedIn']
})
export class HomeComponent implements OnInit {

    isLoggedIn:boolean;


    ngOnInit():any {
        this.isLoggedIn = true;
    }
}