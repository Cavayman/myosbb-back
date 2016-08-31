import {Component} from "@angular/core";
import {ActivatedRoute} from "@angular/router";
import {HTTP_PROVIDERS,Http,Headers,RequestOptions} from '@angular/http';

import {apartmentProfileService} from './apartment.profile.service';
import {DatePipe} from '@angular/common';
import {User} from "../../../shared/models/User";
import{CurrentUserService} from "../../../shared/services/current.user.service";
import{IApartment} from "../../../shared/models/apartment.interface";

@Component({
    selector:'app-profile',
    templateUrl:'src/app/user/ApartmentProfile/apartment.profile.html',
    providers: [ apartmentProfileService],
    styleUrls: ['src/app/user/ApartmentProfile/apartmentStyle.css']
})



export class ApartmentProfileComponent {
    id:any;
    paramsSub:any;
    isUser:boolean = false;
    private currentUser:User;
    private currentApartment:IApartment={apartmentId:0,square:0,number:0,owner:0,house:0,bills:[]};
   private usersInApartment:User[];
    private countOfUsers:number=2;
    private owner:User={userId:0,phoneNumber:'',email:''};


    constructor(private activatedRoute:ActivatedRoute, private apartmentService:apartmentProfileService, private currentUserService:CurrentUserService) {
        this.paramsSub = this.activatedRoute.params.subscribe(params => this.id = parseInt(params['id'], 10));
        console.log("constructor");
this.usersInApartment=[];


    }


    ngOnInit() {
        this.currentUser = this.currentUserService.getUser();
        this.apartmentService.getCurrentApartment(this.id)
            .subscribe(res=>{this.currentApartment=res});
        this.apartmentService.getUsersInApartment(this.id)
            .subscribe(res=>this.usersInApartment=res);
        this.apartmentService.getOwnerInApartment(this.id)
            .subscribe(res=>this.owner=res);
        console.log(this.currentApartment)
        this.countOfUsers=this.usersInApartment.length;
    }

        ngOnDestroy(){
            this.paramsSub.unsubscribe();
        }


}
