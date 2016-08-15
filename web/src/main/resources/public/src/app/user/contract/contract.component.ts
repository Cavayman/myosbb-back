/**
 * Created by Anastasiia Fedorak  8/2/16.
 */
import {Component, OnInit, ViewChild} from "@angular/core";
import {CapitalizeFirstLetterPipe} from "../../../shared/pipes/capitalize-first-letter";
import {TranslatePipe} from "ng2-translate/ng2-translate";
import {DROPDOWN_DIRECTIVES} from "ng2-bs-dropdown/dropdown";
import {ContractService} from "./service/contract-service";
import {Contract} from "../../../shared/models/contract.interface";
import {PageCreator} from "../../../shared/services/page.creator.interface";
import {Observable} from 'rxjs/Observable';
import {MODAL_DIRECTIVES, BS_VIEW_PROVIDERS} from 'ng2-bootstrap/ng2-bootstrap';
import {ModalDirective} from "ng2-bootstrap/ng2-bootstrap";
import {CORE_DIRECTIVES} from "@angular/common";
import {ROUTER_DIRECTIVES} from "@angular/router";
import "rxjs/Rx";
import {SelectProviderComponent} from "../provider/select-provider.component";
import {Provider} from "../../../shared/models/provider.interface";
import {CurrencyComponent} from "./currency.component";

@Component({
    selector: 'myosbb-contract',
    templateUrl: 'src/app/user/contract/contract-table.html',
    pipes: [TranslatePipe, CapitalizeFirstLetterPipe],
    directives: [DROPDOWN_DIRECTIVES],
    providers: [ContractService],
    directives: [MODAL_DIRECTIVES, CORE_DIRECTIVES, ROUTER_DIRECTIVES, SelectProviderComponent, CurrencyComponent, DROPDOWN_DIRECTIVES],
    viewProviders: [BS_VIEW_PROVIDERS]
})
export class ContractComponent implements OnInit{
    private contracts :  Contract[];
    private selected : Contract =  {contractId: null, dateStart:'', dateFinish:'', text: '', price:null, attachment: null, osbb: null, provider:
    { providerId:null, name:'', description:'', logoUrl:'', periodicity:'', type:null, email:'',phone:'', address:''}};

    private newContract : Contract =  {contractId: null, dateStart:'', dateFinish:'', text: '', price:null, attachment: null, osbb: null,
        provider:{ providerId:null, name:'', description:'', logoUrl:'', periodicity:'', type:null, email:'',phone:'', address:''}};
    private pageCreator:PageCreator<Contract>;
    private pageNumber:number = 1;
    private pageList:Array<number> = [];
    private totalPages:number;
    @ViewChild('delModal') public delModal:ModalDirective;
    @ViewChild('editModal') public editModal:ModalDirective;
    @ViewChild('createModal') public createModal:ModalDirective;

    active:boolean = true;
    order:boolean = true;

    private contractId:number;
    private currency:string;
    
    constructor(private _contractService:ContractService){
    }

    ngOnInit():any {
        console.log("init contract cmp");
        this.getContractsByPageNum(this.pageNumber);

    }

    isDateValid(date: string): boolean {
        return /\d{4}-\d{2}-\d{2}/.test(date);
    }
    openEditModal(contract:Contract) {
        this.selected = contract;
        console.log('selected contract: ' + this.selected.contractId);
        this.editModal.show();
    }

    onEditContractSubmit() {
        this.active = false;
        console.log('saving contract: ' + this.selected);
        this._contractService.editAndSave(this.selected);
        this._contractService.getContracts(this.pageNumber);
        this.editModal.hide();
        setTimeout(() => this.active = true, 0);
    }

    closeEditModal() {
        console.log('closing edt modal');
        this.editModal.hide();
    }

    onCreateContractSubmit() {
        this.active = false;
        console.log("new contract: id=" + this.newContract.contractId + "; date start=" + this.newContract.dateStart +
            "; date finish=" +this.newContract.dateFinish + "; price=" + this.newContract.price);
        this._contractService.addContract(this.newContract);
        console.log("add contract");
        this._contractService.getContracts(this.pageNumber);
        this.getContractsByPageNum(this.pageNumber);
        this.createModal.hide();
        setTimeout(() => this.active = true, 0);
    }
    closeCreateModal() {
        console.log('closing create modal');
        this.createModal.hide();
    }
    
    openDelModal(id:number) {
        this.contractId = id;
        console.log('show', this.contractId);
        this.delModal.show();
    }

    closeDelModal() {
        console.log('delete', this.contractId);
        this._contractService.deleteContractById(this.contractId);
        this.getContractsByPageNum(this.pageNumber);
        this.delModal.hide();
    }

    getContractsByPageNum(pageNumber:number) {
        console.log("getContractsByPageNum"+ pageNumber);
        this.pageNumber = +pageNumber;
        this.emptyArray();
        return this._contractService.getContracts(this.pageNumber)
            .subscribe((data) => {
                    this.pageCreator = data;
                    this.contracts = data.rows;
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
        this.getContractsByPageNum(this.pageNumber)
    }

    nextPage() {
        this.pageNumber = this.pageNumber + 1;
        this.getContractsByPageNum(this.pageNumber)
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
        this._contractService.getSortedContracts(this.pageNumber, name, this.order)
            .subscribe((data) => {
                    this.pageCreator = data;
                    this.contracts = data.rows;
                    this.preparePageList(+this.pageCreator.beginPage,
                        +this.pageCreator.endPage);
                    this.totalPages = +data.totalPages;
                },
                (err) => {
                    console.error(err)
                });
    }

    openCreateModal() {
        this.createModal.show();
    }

    onSearch(search:string){
        console.log("inside search: search param" + search);
        this._contractService.findByProviderName(search)
            .subscribe((contracts) => {
                console.log("data: " + contracts);
                this.contracts = contracts;
            });
    }

    editCompany(provider: Provider){
        this.selected.provider = provider;
        console.log("get provider " + provider);
    }

    selectCompany(provider: Provider){
        this.newContract.provider = provider;
    }

    selectCurrency(currency:string){
     this.currency = currency;
    }

}