import {Component} from "@angular/core";
import {ActivatedRoute} from "@angular/router";
import {HTTP_PROVIDERS,Http,Headers,RequestOptions} from '@angular/http';
import {ApartmentModel} from '../apartment/Apartment.model';
import {apartmentProfileService} from './apartment.profile.service';
import {DatePipe} from '@angular/common';
import {User} from "../models/User";
import{CurrentUserService} from "../../../shared/services/current.user.service";

@Component({
    selector:'app-profile',
    templateUrl:'src/app/user/ApartmentProfile/apartment.profile.html',
    providers: [HTTP_PROVIDERS, apartmentProfileService],
    styleUrls: ['src/app/user/ApartmentProfile/apartmentStyle.css']
})



export class ApartmentProfileComponent {
    id:any;
    paramsSub:any;
    isUser:boolean = false;
    currentUser:User;


    constructor(private activatedRoute:ActivatedRoute, private currentApartment:ApartmentModel, private apartmentService:apartmentProfileService, private currentUserService:CurrentUserService) {
        this.paramsSub = this.activatedRoute.params.subscribe(params => this.id = parseInt(params['id'], 10));
        console.log("constructor");
        this.apartmentService.getCurrentApartment(this.id)
            .subscribe(apartment=>(this.currentApartment = apartment));


    }


    ngOnInit() {
        this.currentUser = this.currentUserService.getUser();
    }

        ngOnDestroy(){
            this.paramsSub.unsubscribe();
        }


}
