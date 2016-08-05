/**
 * Created by Anastasiia Fedorak  8/2/16.
 */
import {Component, OnInit, ViewChild} from "@angular/core";
import {CapitalizeFirstLetterPipe} from "../../../shared/pipes/capitalize-first-letter";
import {TranslatePipe} from "ng2-translate/ng2-translate";
import {DROPDOWN_DIRECTIVES} from "ng2-bs-dropdown/dropdown";
import {ProviderService} from "./service/provider-service";
import {Provider} from "../../../shared/models/profider.interface";
import {PageCreator} from "../../../shared/services/page.creator.interface";
import {Observable} from 'rxjs/Observable';
import {MODAL_DIRECTIVES, BS_VIEW_PROVIDERS} from 'ng2-bootstrap/ng2-bootstrap';
import {ModalDirective} from "ng2-bootstrap/ng2-bootstrap";
import {CORE_DIRECTIVES} from "@angular/common";
import {RouteConfig} from "@angular/router-deprecated";
import {ROUTER_DIRECTIVES} from "@angular/router";
import {ProviderTypeComponent} from "./provider_type/provider-type.component";
import {ProviderType} from "../../../shared/models/provider.type.interface";


@Component({
    selector: 'myosbb-provider',
    templateUrl: 'src/app/user/provider/provider-table.html',
    drtemplateUrl: 'src/app/user/provider/provider-table.html',
    pipes: [TranslatePipe, CapitalizeFirstLetterPipe],
    directives: [DROPDOWN_DIRECTIVES],
    providers: [ProviderService],
    directives: [MODAL_DIRECTIVES, CORE_DIRECTIVES, ROUTER_DIRECTIVES, ProviderTypeComponent],
    viewProviders: [BS_VIEW_PROVIDERS]
})
export class ProviderComponent implements OnInit{
    private providers :  Provider[];
    private selected : Provider =  {providerId:null, name:'', description:'', logoUrl:'', periodicity:'', type:null};
    private typeDisplay : string;
    private pageCreator:PageCreator<Provider>;
    private pageNumber:number = 1;
    private pageList:Array<number> = [];
    private totalPages:number;
    @ViewChild('delModal') public delModal:ModalDirective;
    @ViewChild('editModal') public editModal:ModalDirective;
    active:boolean = true;
    order:boolean = true;

    private providerId:number;
    
    constructor(private _providerService:ProviderService){

    }

    setType(event){
        console.log("set type" + event);
        this.selected.type = event.id;
        console.log("selected.type=" + this.selected.type + JSON.stringify(this.selected));
        this.typeDisplay = event.type;
        console.log("name: " + this.typeDisplay)
    }

    ngOnInit():any {
        console.log("init provider cmp");
        this.getProvidersByPageNum(this.pageNumber);
    }

    openEditModal(provider:Provider) {
        this.selected = provider;
        console.log('selected provider: ' + this.selected.name);
        this.editModal.show();
    }

    onEditProviderSubmit() {
        this.active = false;
        console.log('saving provider: ' + this.selected);
        this._providerService.editAndSave(this.selected);
        this._providerService.getProviders(this.pageNumber);
        this.editModal.hide();
        setTimeout(() => this.active = true, 0);
    }

    closeEditModal() {
        console.log('closing edt modal');
        this.editModal.hide();
    }

    openDelModal(id:number) {
        this.providerId = id;
        console.log('show', this.providerId);
        this.delModal.show();
    }

    closeDelModal() {
        console.log('delete', this.providerId);
        this._providerService.deleteProviderById(this.providerId);
        this.getProvidersByPageNum(this.pageNumber);
        this.delModal.hide();
    }

    getProvidersByPageNum(pageNumber:number) {
        console.log("getProvidersByPageNum"+ pageNumber);
        this.pageNumber = +pageNumber;
        this.emptyArray();
        return this._providerService.getProviders(this.pageNumber)
            .subscribe((data) => {
                    this.pageCreator = data;
                    this.providers = data.rows;
                    this.preparePageList(+this.pageCreator.beginPage,
                        +this.pageCreator.endPage);
                    this.totalPages = +data.totalPages;
                },
                (err) => {
                    console.error(err)
                });
    };

    prevPage() {
        this.pageNumber = this.pageNumber - 1;
        this.getProvidersByPageNum(this.pageNumber)
    }

    nextPage() {
        this.pageNumber = this.pageNumber + 1;
        this.getProvidersByPageNum(this.pageNumber)
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


    sortBy(name:string) {
        console.log('sorted by ', name);
        this.order = !this.order;
        console.log('order by asc', this.order);
        this.emptyArray();
        this._providerService.getSortedProviders(this.pageNumber, name, this.order)
            .subscribe((data) => {
                    this.pageCreator = data;
                    this.providers = data.rows;
                    this.preparePageList(+this.pageCreator.beginPage,
                        +this.pageCreator.endPage);
                    this.totalPages = +data.totalPages;
                },
                (err) => {
                    console.error(err)
                });
    }
    

}