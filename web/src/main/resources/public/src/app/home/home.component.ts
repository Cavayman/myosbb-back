import {Component, OnInit} from "@angular/core";
import {ROUTER_DIRECTIVES} from "@angular/router";

import {UserComponent} from "../user/user.component";
import {HeaderComponent} from "../home/header/header.component";

import {SideBarMenuComponent} from "../home/sidebar_menu/sidebar_menu.component";
import {VoteComponent} from "./voting/vote.component";
import {LoginStat} from "../../shared/services/login.stats";
import {Vote} from './voting/vote';

@Component({
    selector: 'app-home',
    templateUrl: './src/app/home/home.html',
    styleUrls: ['./src/app/home/home.css'],
    directives: [ROUTER_DIRECTIVES, UserComponent, HeaderComponent, SideBarMenuComponent, VoteComponent],
    inputs: ['isLoggedIn'],
    outputs: ['isLoggedIn'],
    providers: [LoginStat]
})

export class HomeComponent implements OnInit {
    voteArr: Vote[];
    isLoggedIn:boolean;

    constructor(private _loginStat:LoginStat) {
        this.isLoggedIn = true;
    }

    ngOnInit():any {
    this.voteArr = [];
        this._loginStat.loggedInObserver$
            .subscribe(stat => {
                this.isLoggedIn = stat;
            })
    }

    addVoting(vote: Vote): void {
        //console.log("TOP");
        //console.log("Object: isPrivate:" + vote.isPrivate + "   question:" + vote.question  +"   opp:" +vote.options.toString());
        this.voteArr.push(vote);
    }

    getResult(answer: string):void {      //DELETE ME
        console.log("Ви проголосували за " + answer);
    }

    printHello(): void {             //DELETE ME
        console.log("Привіт");
    }   
}