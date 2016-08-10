///<reference path="../../../../node_modules/@angular/http/src/http.d.ts"/>
import {Component, ViewChild} from '@angular/core'
import {HTTP_PROVIDERS, Http, Headers, RequestOptions} from "@angular/http";
import {ApartmentModel} from "./Apartment.model";
import {IApartmentModel} from "./Apartment.model";
import {ROUTER_DIRECTIVES,Router} from "@angular/router";
import {ApartmentService} from './apartment.service'
import "rxjs/add/operator/toPromise";
import {MODAL_DIRECTIVES, BS_VIEW_PROVIDERS, ModalDirective} from "ng2-bootstrap/ng2-bootstrap";
import {CORE_DIRECTIVES} from "@angular/common";

@Component({
    selector: 'my-user-apartment',
    templateUrl: 'src/app/user/apartment/apartment.html',
    providers: [HTTP_PROVIDERS,ApartmentService],
    directives: [ROUTER_DIRECTIVES,MODAL_DIRECTIVES, CORE_DIRECTIVES],
    styleUrls: ['src/app/user/apartment/styles.css'],
    viewProviders: [BS_VIEW_PROVIDERS]
})
export class UserApartmentComponent {
    Items:ApartmentModel[];
    private selectedApartment:ApartmentModel;
    private emptyApartment:ApartmentModel;
    //={apartmentId:null,square:null,number:null};
    @ViewChild('editModal')
    public editModal:ModalDirective;
    @ViewChild('addModal')
    public addModal:ModalDirective;
    active:boolean = true;
    activeAdd:boolean = true;





    constructor( private apartmentService:ApartmentService) {
this.Items=[];
        this.selectedApartment = {};
        this.emptyApartment={number:'',square:''};
        this.selectedApartment.number=0;
        this.selectedApartment.square=0;
        // this.selectedApartment= new ApartmentModel({apartmentId:null,square:null,number:null,house:null,user:null,users:[],bills:[]}) ;
    }

    private createrandomAp():ApartmentModel {
        let am = new ApartmentModel();
        am.number = Math.round((Math.random() * 100)) + 1;
        am.square = Math.round(Math.random() * 300) + 1;
        //am.user=new JSON.Object("userId:6");
        return am;
    }

    onUpdateClick(am:ApartmentModel) {
        console.log(this.Items[0]);
    }
    ngOnInit(){
        this.apartmentService.getAllApartments()
            .subscribe(items=> this.Items=items);
    }
    
    onAddClick(){
        let am= this.createrandomAp();
        console.log("adding apartment"+am);
        //this.Items.push(am);
        this.apartmentService.addApartment(am)
            .then(am=>this.Items.push(am));
    }


    onDeleteClick(am) {
        this.apartmentService.deleteApartment(am)
            .then(am=>this.Items.splice(this.Items.indexOf(am, 0), 1));

    }
    openEditModal(am:ApartmentModel) {
        this.selectedApartment=am;
        console.log(am);
      console.log('selected Apartment: ' + this.selectedApartment.square );
       this.editModal.show();
    }

    onEditApartmentSubmit() {
        this.active = false;
        console.log('saving Apartment: ' + this.selectedApartment.apartmentId);
      this.apartmentService.editAndSave(this.selectedApartment);
       this.apartmentService.getAllApartments();
        this.editModal.hide();
        setTimeout(() => this.active = true, 0);
    }

    onAddApartmentSubmit(){
console.log("good work!!");
        console.log("empty !"+this.emptyApartment.square);
        console.log('closing edit modal');
        let userid = Math.round(Math.random()*195)+6;
        this.emptyApartment.user={userId:userid};
        this.addModal.hide();
        this.apartmentService.addApartment(this.emptyApartment)
            .then(emptyApartment=>this.Items.push(emptyApartment));

    }

    openAddModal(){
        this.addModal.show();
    }

    closeEditModal() {

    }


}

    


    
    
    
