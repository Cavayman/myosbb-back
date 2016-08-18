import {Component, OnInit, Output} from '@angular/core'
import {EventEmitter} from '@angular/core';
import {User} from "../../shared/models/User";
import {RegisterService} from "./register.service";
import {ROUTER_DIRECTIVES} from "@angular/router";
import MaskedInput from 'angular2-text-mask';
import emailMask from 'node_modules/text-mask-addons/dist/emailMask.js'
@Component({
    selector: 'app-register',
    templateUrl: 'src/app/registration/registration.html',
    styleUrls:['assets/css/registration/registration.css'],
    providers:[RegisterService],
    directives: [ROUTER_DIRECTIVES,MaskedInput]
})
export class RegistrationComponent implements OnInit  {
    newUser:User=new User();

     public emailMask =emailMask;
    public textmask=[/[A-zА-яІ-і]/,/[A-zА-яІ-і]/,/[A-zА-яІ-і]/,/[A-zА-яІ-і]/,/[A-zА-яІ-і]/,/[A-zА-яІ-і]/,/[A-zА-яІ-і]/,/[A-zА-яІ-і]/,/[A-zА-яІ-і]/,/[A-zА-яІ-і]/,/[A-zА-яІ-і]/,/[A-zА-яІ-і]/,/[A-zА-яІ-і]/,/[A-zА-яІ-і]/,/[A-zА-яІ-і]/,/[A-zА-яІ-і]/,/[A-zА-яІ-і]/,/[A-zА-яІ-і]/,/[A-zА-яІ-і]/,/[A-zА-яІ-і]/,/[A-zА-яІ-і]/,/[A-zА-яІ-і]/,/[A-zА-яІ-і]/,/[A-zА-яІ-і]/,/[A-zА-яІ-і]/];
    public phoneMask=['(', /[0]/, /\d/, /\d/, ')', ' ', /\d/, /\d/, /\d/, '-', /\d/, /\d/, /\d/, /\d/];
    confirmPassword:number="";
    error:boolean=false;
    errorConfirm:boolean=false;
    errorMsg="";

registered:boolean
    constructor(private registerService:RegisterService) {
    this.newUser.password="";
    }

    onSubmit(){
        this.registerService.sendUser(this.newUser).subscribe(
            data => {
               this.registered=true;
                this.newUser=new User();
                console.log(this.newUser)
            },
            error=>console.log(error)
        );
    }
    
    confirmPass(){
    this.error=false;
    var password=this.confirmPassword;
    var password2=this.newUser.password;
   if(password.length!=0){
     if(password!=password2)
    { 
   
       this.errorMsg="Passwords don't match. Please try again";
        this.errorConfirm=true;
        this.confirmPassword="";
    return;
    }
   }
 if(password2.length<4||password2.length>16)
    {
     this.errorMsg="Password cannot be shorter than 4 and longer than 16 characters";
    this.error=true;
    this.errorConfirm=false;
    }else{this.errorConfirm=false;
    }
  


    }

}