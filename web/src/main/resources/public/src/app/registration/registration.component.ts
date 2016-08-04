import {Component, OnInit, Output} from '@angular/core'
import {EventEmitter} from '@angular/core';
import {User} from "../user/users/User";
import {RegisterService} from "./register.service";

@Component({
    selector: 'app-register',
    templateUrl: 'src/app/registration/registration.html',
    styleUrls:['assets/css/registration/registration.css'],
    providers:[RegisterService]
})
export class RegistrationComponent  {
    newUser:User=new User();

    constructor(private registerService:RegisterService) {

    }

    onSubmit(){
        this.registerService.sendUser(this.newUser).subscribe(
            data => {
               //this.registered=true;
                this.newUser=new User();
            },
            error=>console.log(error)
        );
    }



}