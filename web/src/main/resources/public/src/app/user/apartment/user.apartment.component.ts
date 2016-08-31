///<reference path="../../../../node_modules/@angular/http/src/http.d.ts"/>
import {Component, ViewChild,Input} from '@angular/core'
import {HTTP_PROVIDERS, Http, Headers, RequestOptions} from "@angular/http";
import {ApartmentModel} from "./Apartment.model";
import {ROUTER_DIRECTIVES} from "@angular/router";
import {ApartmentService} from './apartment.service'
import "rxjs/add/operator/toPromise";
import {MODAL_DIRECTIVES, BS_VIEW_PROVIDERS, ModalDirective} from "ng2-bootstrap/ng2-bootstrap";
import {CORE_DIRECTIVES} from "@angular/common";
import {TranslatePipe} from "ng2-translate/ng2-translate";
import {User} from "../../../shared/shared/models/User";
import {CurrentUserService} from "../../../shared/services/current.user.service";
import {PageCreator} from "../../../shared/services/page.creator.interface";
@Component({
    selector: 'my-user-apartment',
    templateUrl: 'src/app/user/apartment/apartment.html',
    providers: [ApartmentService],
    directives: [ROUTER_DIRECTIVES,MODAL_DIRECTIVES, CORE_DIRECTIVES],
    styleUrls: ['src/app/user/apartment/styles.css'],
    viewProviders: [BS_VIEW_PROVIDERS],
    pipes:[TranslatePipe],
    inputs:['isAdmin']
})
export class UserApartmentComponent {
    isAdmin:boolean=false;
    Items:ApartmentModel[];
    private selectedApartment:ApartmentModel;
    private emptyApartment:ApartmentModel;
    @ViewChild('editModal')
    public editModal:ModalDirective;
    @ViewChild('addModal')
    public addModal:ModalDirective;
    active:boolean = true;
    private pageCreator:PageCreator<ApartmentModel>;
    private pageNumber:number = 1;
    private pageList:Array<number> = [];
    private totalPages:number;
    order:boolean = true;
    private defaultSorter:string='number';
    private currentUser:User;




    constructor( private apartmentService:ApartmentService,private currentUserService:CurrentUserService) {
console.log("init items");
        this.Items=[];

    }


    ngOnInit(){

        this.getApartmentsByPageNum(this.pageNumber);
        this.currentUser = this.currentUserService.getUser();
        //console.log("current user: "+this.currentUser.apartment.number);

    }
    

    onDeleteClick(am) {
        this.apartmentService.deleteApartment(am)
            .then(am=>this.Items.splice(this.Items.indexOf(am, 0), 1));

    }
    openEditModal(am:ApartmentModel) {
        this.selectedApartment=am;
       // console.log(am);
     // console.log('selected Apartment: ' + this.selectedApartment.square );
       this.editModal.show();
    }

    onEditApartmentSubmit() {
        this.active = false;
        //console.log('saving Apartment: ' + this.selectedApartment.apartmentId);
      this.apartmentService.editAndSave(this.selectedApartment);
       this.apartmentService.getAllApartments();
        this.editModal.hide();
        setTimeout(() => this.active = true, 0);
    }

    onAddApartmentSubmit(){

        this.addModal.hide();
        this.apartmentService.addApartment(this.emptyApartment)
            .then(emptyApartment=>this.Items.push(emptyApartment));

    }

    openAddModal(){
        this.addModal.show();
    }
    sortBy(value:string){
        console.log('sorted by ', value);
        this.order = !this.order;
        this.defaultSorter=value;
        console.log('order by asc', this.order);
        this.emptyArray();
        this.apartmentService.getSortedApartments(this.pageNumber, value, this.order)
            .subscribe((data) => {
                    this.pageCreator = data;
                    this.Items = data.rows;
                    this.preparePageList(+this.pageCreator.beginPage,
                        +this.pageCreator.endPage);
                    this.totalPages = +data.totalPages;
                },
                (err) => {
                    console.error(err)
                });   
    }


    getApartmentsByPageNum(pageNumber:number) {
        console.log("getApartmentssByPageNum"+ pageNumber);
        this.pageNumber = +pageNumber;
        this.emptyArray();
        return this.apartmentService.getSortedApartments(this.pageNumber,this.defaultSorter,this.order)
            .subscribe((data) => {
                    this.pageCreator = data;
                    this.Items = data.rows;
                    this.preparePageList(+this.pageCreator.beginPage,
                        +this.pageCreator.endPage);
                    this.totalPages = +data.totalPages;
               // console.log("ITEM 0 =   "+Items[0]);
                },
                (err) => {
                    console.error(err)
                });
    };

    prevPage() {
        this.pageNumber = this.pageNumber - 1;
        this.getApartmentsByPageNum(this.pageNumber)
    }

    nextPage() {
        this.pageNumber = this.pageNumber + 1;
        this.getApartmentsByPageNum(this.pageNumber)
    }

    emptyArray() {
        while (this.pageList.length) {
            this.pageList.pop();
        }
    }

    preparePageList(start:number, end:number) {
        for (let i = start; i <= end; i++) {
            this.pageList.push(i);
        }
    }
    getApartmentsByPageNumWithNumber(pageNumber:number,numberOfApartment:number) {
        console.log("getApartmentssByPageNum"+ pageNumber+"with apartment number ="+numberOfApartment);
        this.pageNumber = +pageNumber;
        this.emptyArray();
        return this.apartmentService.getSortedApartmentsWithNumber(this.pageNumber,this.defaultSorter,this.order,numberOfApartment)
            .subscribe((data) => {
                    this.pageCreator = data;
                    this.Items = data.rows;
                    this.preparePageList(+this.pageCreator.beginPage,
                        +this.pageCreator.endPage);
                    this.totalPages = +data.totalPages;
                    // console.log("ITEM 0 =   "+Items[0]);
                },
                (err) => {
                    console.error(err)
                });
    };

    searchByNumber(searchTerm:number){
        this.getApartmentsByPageNumWithNumber(this.pageNumber,searchTerm);
        console.log("search"+searchTerm);
    }
    clearSearchBox(searchterm){
        if(searchterm.value==='')
            this.getApartmentsByPageNum(this.pageNumber);

    }

}

    


    
    
    
