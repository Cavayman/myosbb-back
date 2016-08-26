import {Component, OnInit} from "@angular/core";
import {ROUTER_DIRECTIVES} from "@angular/router";

import {VoteComponent} from "../voting/vote.component";

@Component({
    selector: 'home-wall',
    templateUrl: './src/app/home/home_wall/home.wall.html',
    styleUrls: ['./src/app/home/home_wall/home.wall.css'],
    directives: [ROUTER_DIRECTIVES, VoteComponent]
})

export class HomeWallComponent implements OnInit {
  
    isLoggedIn:boolean;

    constructor() {
    }

    ngOnInit():any {

    }

    printHello(): void {             //DELETE ME
        console.log("Привіт");
    }   
}