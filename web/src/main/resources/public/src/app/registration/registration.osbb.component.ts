import {Component, OnInit, Output} from '@angular/core'
import {EventEmitter} from '@angular/core';
import {Osbb} from "../../shared/models/osbb";
import {RegisterOsbbService} from "./register.osbb.service";
import {ROUTER_DIRECTIVES} from "@angular/router";
import {GoogleplaceDirective} from "./googleplace.directive";
@Component({
    selector: 'app-register',
    templateUrl: 'src/app/registration/registration.osbb.html',
    styleUrls:['assets/css/registration/registration.css'],
    providers:[RegisterOsbbService],
    directives: [ROUTER_DIRECTIVES,GoogleplaceDirective]
})
export class RegistrationOsbbComponent  {
    newOsbb:Osbb =  new Osbb;
    constructor(private registerOsbbService:RegisterOsbbService) {}
    registeredOsbb: boolean;

    onSubmit(){
        this.registerOsbbService.sendOsbb(this.newOsbb).subscribe(
            data => {
                this.registeredOsbb=true;
                this.newOsbb=new Osbb();
                console.log(this.newOsbb)
            },
            error=>console.log(error)
        );
    }
    get diagnostic() { return JSON.stringify(this.newOsbb); }
     
    public address : Object;
    getAddress(place:Object) {       
        this.address = place['formatted_address'];
        var location = place['geometry']['location'];
        var lat =  location.lat();
        var lng = location.lng();
        console.log("Address Object", place);
    }
}