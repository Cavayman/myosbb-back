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
import {CORE_DIRECTIVES, NgClass} from "@angular/common";
import {ROUTER_DIRECTIVES} from "@angular/router";
import "rxjs/Rx";
import {SelectProviderComponent} from "../provider/select-provider.component";
import {Provider} from "../../../shared/models/provider.interface";
import {CurrencyComponent} from "./currency.component";
import {MailService} from "../../../shared/services/mail.sender.service";
import {Mail} from "../../../shared/models/mail.interface";
import {ActiveFilter} from "../../../shared/pipes/active.filter";

@Component({
    selector: 'myosbb-contract',
    templateUrl: 'src/app/user/contract/contract-table.html',
    styleUrls: ['src/app/user/contract/contract.css'],
    pipes: [TranslatePipe, CapitalizeFirstLetterPipe, ActiveFilter],
    directives: [DROPDOWN_DIRECTIVES],
    providers: [ContractService, MailService],
    directives: [MODAL_DIRECTIVES, CORE_DIRECTIVES, ROUTER_DIRECTIVES, SelectProviderComponent, CurrencyComponent, NgClass, DROPDOWN_DIRECTIVES],
    viewProviders: [BS_VIEW_PROVIDERS]
})
export class ContractComponent implements OnInit{
    private contracts :  Contract[];
    private selected : Contract =  {contractId: null, dateStart:'', dateFinish:'', text: '', price:null, priceCurrency: 'UAH',attachment: null, osbb: null,  active: false, provider:
    {providerId:null, name:'', description:'', logoUrl:'', periodicity:'', type:null, email:'',phone:'', address:'', schedule:'', active: false}};

    private newContract : Contract =  {contractId: null, dateStart:'', dateFinish:'', text: '', price:null, priceCurrency: 'UAH',attachment: null, osbb: null,  active: false, provider:
    {providerId:null, name:'', description:'', logoUrl:'', periodicity:'', type:null, email:'',phone:'', address:'', schedule:'', active: false}};

    private pageCreator:PageCreator<Contract>;
    private pageNumber:number = 1;
    private pageList:Array<number> = [];
    private totalPages:number;
    @ViewChild('delModal') public delModal:ModalDirective;
    @ViewChild('editModal') public editModal:ModalDirective;
    @ViewChild('createModal') public createModal:ModalDirective;

    active:boolean = true;
    order:boolean = true;

    onlyActive: boolean = true;

    private contractId:number;

    constructor(private _contractService:ContractService, private _mailService: MailService){
    }

    ngOnInit():any {
        console.log("init contract cmp");
        this.getContractsByPageNumAndState(this.pageNumber);
        console.log("on init only active", this.onlyActive);
    }
    isDateValid(date: string): boolean {
        return /\d{4}-\d{2}-\d{2}/.test(date);
    }

    openEditModal(contract:Contract) {
        this.selected = contract;
        console.log('selected contract: ' + this.selected.contractId);
        this.editModal.show();
    }
    closeEditModal() {
        console.log('closing edt modal');
        this.editModal.hide();
    }
    onEditContractSubmit() {
        this.active = false;
        console.log('saving contract: ', this.selected);
        this._contractService.editAndSave(this.selected);
        this._contractService.getContracts(this.pageNumber);
        this.editModal.hide();
        setTimeout(() => this.active = true, 0);
    }

    openCreateModal() {
        this.createModal.show();
    }
    closeCreateModal() {
        console.log('closing create modal');
        this.createModal.hide();
    }
    onCreateContractSubmit() {
        this.active = false;
        console.log("creating ", this.newContract);
        this._contractService.addContract(this.newContract);
        console.log("add contract", this.newContract);
        this._contractService.getContracts(this.pageNumber);
        this.getContractsByPageNumAndState(this.pageNumber);
        this.createModal.hide();
        setTimeout(() => this.active = true, 0);
    }

    openDelModal(id:number) {
        this.contractId = id;
        console.log('show', this.contractId);
        this.delModal.show();
    }
    closeDelModal() {
        console.log('delete', this.contractId);
        this._contractService.deleteContractById(this.contractId);
        this.getContractsByPageNumAndState(this.pageNumber);
        this.delModal.hide();
    }

    // getContractsByPageNum(pageNumber:number) {
    //     console.log("getContractsByPageNum"+ pageNumber);
    //     this.pageNumber = +pageNumber;
    //     this.emptyArray();
    //     return this._contractService.getContracts(this.pageNumber)
    //         .subscribe((data) => {
    //                 this.pageCreator = data;
    //                 this.contracts = data.rows;
    //                 this.preparePageList(+this.pageCreator.beginPage,
    //                     +this.pageCreator.endPage);
    //                 this.totalPages = +data.totalPages;
    //             },
    //             (err) => {
    //                 console.error(err)
    //             });
    // };

    prevPage() {
        this.pageNumber = this.pageNumber - 1;
        this.getContractsByPageNumAndState(this.pageNumber)
    }
    nextPage() {
        this.pageNumber = this.pageNumber + 1;
        this.getContractsByPageNumAndState(this.pageNumber)
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
        this._contractService.getSortedActiveContracts(this.pageNumber, name, this.order, this.onlyActive)
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
        console.log("get provider ", provider);
    }
    selectCompany(provider: Provider){
        this.newContract.provider = provider;
        console.log("insert provider ", this.newContract.provider, provider);
    }

    selectCurrency(currency:string){
     this.selected.priceCurrency = currency;
        this.newContract.priceCurrency = currency;
        console.log("select currency: ", this.selected.priceCurrency);
    }

    getContractsByPageNumAndState(pageNumber:number){
        console.log("getContractsByPageNum "+ pageNumber + "; only active=" + this.onlyActive);
        this.pageNumber = +pageNumber;
        this.emptyArray();
        return this._contractService.getContractsByState(this.pageNumber, this.onlyActive)
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

    onOnlyActive(){
        this.onlyActive = !this.onlyActive;
        console.log("change active filter, onlyActive=", this.onlyActive);
        if (this.onlyActive == true) {console.log("listing only active contracts");
        } else {console.log("listing all contracts");}
        this.getContractsByPageNumAndState(this.pageNumber);
    }
}