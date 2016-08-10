import {Component} from "@angular/core";
import {ActivatedRoute} from "@angular/router";
import {HTTP_PROVIDERS,Http,Headers,RequestOptions} from '@angular/http';
import {singleApartmentModel} from './Apartment.model';
import {apartmentProfileService} from './apartment.profile.service';
import {DatePipe} from '@angular/common';
@Component({
    selector:'app-profile',
    templateUrl:'src/app/user/ApartmentProfile/apartment.profile.html',
    providers: [HTTP_PROVIDERS, apartmentProfileService,singleApartmentModel]
})

export class ApartmentProfileComponent{
    id: any;
    paramsSub: any;
    apartmentOwner:any;
    firstName:string;
    bill:number=0;
    countOfUsers:number=0;
    time:any;
    clicked:boolean=false;
    
    constructor(private activatedRoute: ActivatedRoute,private currentApartment:singleApartmentModel,private apartmentService:apartmentProfileService){
       this.currentApartment= {apartmentId:null,square:0,number:0,house:{},user:{},bills:[],users:[{firsName:"svitlana"}]};
        this.paramsSub = this.activatedRoute.params.subscribe(params => this.id = parseInt(params['id'], 10));
        console.log("constructor");
        this.apartmentService.getCurrentApartment(this.id)
          .subscribe(item=>(this.currentApartment=item));



        

    }


    ngOnInit() {
        this.getApartmentOwner();
        //this.bill = this.currentApartment.bills.length;
       this.countOfUsers= this.currentApartment.users.length;
        console.log("bill"+this.currentApartment.bills.length);
        //this.getApartmentOwner();
        console.log("ІНІТ");
       // console.log(this.currentApartment);
    }
  
    btnclick(){
        this.getApartmentOwner();
        console.log("this apartment");
        console.log(this.currentApartment);
        console.log("this user");
        console.log(this.apartmentOwner);
        console.log("count of bills"+this.currentApartment.bills.length);
        
    }
    getCountOfBiils(){
       // console.log("count: "+this.currentApartment.bills.length);

    }
    
    onUsersClick(){
        this.clicked=!this.clicked;
    }

    addUsers()
    {
        
    }



    getApartmentOwner(){

        this.apartmentOwner = this.currentApartment.user;
        this.firstName=this.apartmentOwner.firstName;
}

    
    
    ngOnDestroy() {
        this.paramsSub.unsubscribe();
    }

}