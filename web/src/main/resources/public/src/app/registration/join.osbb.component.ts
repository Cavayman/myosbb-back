import {Component, OnInit, Output} from '@angular/core'
import {EventEmitter} from '@angular/core';
import {IOsbb,Osbb} from "../../shared/models/osbb";
import {JoinOsbbService} from "./join.osbb.service";
import {ROUTER_DIRECTIVES} from "@angular/router";

@Component({
    selector: 'app-register',
    templateUrl: 'src/app/registration/join.osbb.html',
    styleUrls: ['assets/css/registration/registration.css'],
    providers: [JoinOsbbService],
    directives: [ROUTER_DIRECTIVES]
})
export class JoinOsbbComponent {
    osbbArr:IOsbb[];
    numbersOfHouses = ['11','12','13'];
    numbersOfAppartments = ['1','2','3','4','5','6','7','8','9','10','11','12','13','14','15','16','17','18','19','20','21','22','23','24','25','26'];
    joinedOsbb:boolean;

    constructor( private joinOsbbService: JoinOsbbService) { 
        this.osbbArr = [];
    }
    ngOnInit() {
        this.joinOsbbService.getAllOsbb().then(osbbArr => this.osbbArr = osbbArr);
    }
    onSubmit(){
        this.joinedOsbb=true;
    }


}