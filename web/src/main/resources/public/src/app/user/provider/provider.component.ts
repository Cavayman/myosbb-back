/**
 * Created by Anastasiia Fedorak  8/2/16.
 */
import {Component, OnInit, ViewChild, Input} from "@angular/core";
import {CapitalizeFirstLetterPipe} from "../../../shared/pipes/capitalize-first-letter";
import {TranslatePipe, TranslateService} from "ng2-translate/ng2-translate";
import {DROPDOWN_DIRECTIVES} from "ng2-bs-dropdown/dropdown";
import {ProviderService} from "./service/provider-service";
import {PageCreator} from "../../../shared/services/page.creator.interface";
import {Observable} from 'rxjs/Observable';
import {MODAL_DIRECTIVES, BS_VIEW_PROVIDERS, BUTTON_DIRECTIVES} from 'ng2-bootstrap/ng2-bootstrap';
import {ModalDirective} from "ng2-bootstrap/ng2-bootstrap";
import {CORE_DIRECTIVES, NgClass, FORM_DIRECTIVES} from "@angular/common";
import {BUTTON_DIRECTIVES } from 'ng2-bootstrap/ng2-bootstrap';
import {SELECT_DIRECTIVES} from "ng2-select/ng2-select";
import {ROUTER_DIRECTIVES, Router} from "@angular/router";
import {ProviderTypeComponent} from "./provider_type/provider-type.component";
import {ProviderType} from "../../../shared/models/provider.type.interface";
import {ProviderTypeService} from "./provider_type/service/provider-type.service";
import {Provider} from "../../../shared/models/provider.interface";
import {Mail} from "../../../shared/models/mail.interface";
import {MailService} from "../../../shared/services/mail.sender.service";
import {SelectItem} from "../../../shared/models/ng2-select-item.interface";
import {HeaderComponent} from "../../header/header.component";
import {PeriodicityItems} from "./periodicity.const";
import {ActiveFilter} from "../../../shared/pipes/active.filter";


@Component({
    selector: 'myosbb-provider',
    templateUrl: 'src/app/user/provider/provider-table.html',
    pipes: [TranslatePipe, CapitalizeFirstLetterPipe, ActiveFilter],
    directives: [DROPDOWN_DIRECTIVES],
    providers: [ProviderService, MailService],
    directives: [MODAL_DIRECTIVES, CORE_DIRECTIVES, ROUTER_DIRECTIVES, ProviderTypeComponent,
        SELECT_DIRECTIVES, NgClass, FORM_DIRECTIVES, BUTTON_DIRECTIVES ],
    viewProviders: [BS_VIEW_PROVIDERS]
})
export class ProviderComponent implements OnInit{
    private providers :  Provider[];
    private selected : Provider =  {providerId:null, name:'', description:'', logoUrl:'', periodicity:'', type:{providerTypeId: null, providerTypeName: ''},
        email:'',phone:'', address:'', schedule: '', active: false};
        private newProvider : Provider =  {providerId:null, name:'', description:'', logoUrl:'', periodicity:'', type:{providerTypeId: null, providerTypeName: ''},
        email:'',phone:'', address:'', schedule: '', active: false};
    private pageCreator:PageCreator<Provider>;
    private pageNumber:number = 1;
    private pageList:Array<number> = [];
    private totalPages:number;
    @ViewChild('delModal') public delModal:ModalDirective;
    @ViewChild('editModal') public editModal:ModalDirective;
    @ViewChild('createModal') public createModal:ModalDirective;
    active:boolean = true;
    order:boolean = true;

    onlyActive: boolean = true;

    private providerId:number;
    private periodicities: SelectItem[] = [];

    private mail : Mail = {to:'', subject: '', text: ''};
    constructor(private _providerService:ProviderService, private _mailService: MailService,private router: Router){
    }

    ngOnInit():any {
        console.log("init provider cmp");
        console.log("periodicity items:", PeriodicityItems);
        for (let i=0; i<PeriodicityItems.length; i++){
            this.periodicities.push(PeriodicityItems[i]);
        }
        this.getPeriodicitiesTranslation();
        console.log('readable periodicities: ', this.periodicities);
        this.getProvidersByPageNumAndState(this.pageNumber);
    }

    setType(event){
        console.log("set type" + event);
        this.selected.type = event;
        this.newProvider.type = event;
        console.log("selected.type=" + this.selected.type.providerTypeName + JSON.stringify(this.selected));
    }

    public onSelectPeriodicity(value:SelectItem):void {
        console.log("value: ", value);
        this.selected.periodicity = this.backToConst(value);
        this.newProvider.periodicity = this.backToConst(value);
        console.log("set periodicity: ", this.selected.periodicity);
    }

    public onRemove(value:SelectItem):void {
        console.log('Removed value is: ', value);
    }

    public onType(value:SelectItem):void {
        console.log('New search input: ', value);
    }

    public onRefresh(value:SelectItem):void {
        if (value.text != null)
        this.selected.periodicity = value.text;
    }

    openEditModal(provider:Provider) {
        this.selected = provider;
        console.log('selected provider: ' + this.selected);
        this.editModal.show();
    }

    onEditProviderSubmit() {
        this.active = false;
        console.log('saving provider: ' + this.selected);
        this._providerService.editAndSave(this.selected);
        console.log("save provider: ", this.selected);
        this.getProvidersByPageNumAndState(this.pageNumber);
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
        this.getProvidersByPageNumAndState(this.pageNumber);
        this.delModal.hide();
    }

    getPeriodicitiesTranslation(){
        console.log("got lang",  HeaderComponent.translateService.currentLang);
        for (let i=0; i < this.periodicities.length; i++){
            HeaderComponent.translateService.get(this.periodicities[i].text)
                .subscribe((data : string) => {
                    this.periodicities[i].text = data;
                    console.log("periodicity =", this.periodicities[i]);
                })
        }
        console.log("periodicities: ", this.periodicities);
    }

    backToConst(item: SelectItem): string{
        var items : SelectItem[] =
            [{id: 1, text: 'ONE_TIME'},
                {id: 2, text: 'PERMANENT_DAYLY'},
                {id: 3, text: 'PERMANENT_WEEKLY'},
                {id: 4, text: 'PERMANENT_MONTHLY'},
                {id: 5, text: 'PERMANENT_YEARLY'}];
        for (let i=0; i<items.length; i++){
            if (item.id === items[i].id) {
                console.log("const: ", items[i].text);
                return items[i].text;
            }
        }
    }

    prevPage() {
        this.pageNumber = this.pageNumber - 1;
        this.getProvidersByPageNumAndState(this.pageNumber)
    }

    nextPage() {
        this.pageNumber = this.pageNumber + 1;
        this.getProvidersByPageNumAndState(this.pageNumber)
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
        this._providerService.getSortedActiveProviders(this.pageNumber, name, this.order, this.onlyActive)
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

    onSearch(search:string){
        console.log("inside search: search param" + search);
        this._providerService.findProviderByNameOrDescription(search)
            .subscribe((providers) => {
                console.log("data: " + providers);
                this.providers = providers;
            });
    }

    onSendMessage(){
        console.log("sending...");
        this.mail = {to: "aska.fed@gmail.com", subject: "TEST", text: "Success!"};
        this._mailService.sendMail(this.mail);
    }

    onCreateProviderSubmit(){
        this.active = false;
        console.log("creating ", this.newProvider);
        this._providerService.editAndSave(this.newProvider);
        this._mailService.sendMail({
            to: this.newProvider.email,
            subject: 'PRIVET',
            text: 'Welcome on the board'
        });
        console.log("add provider", this.newProvider);
        this._providerService.getProviders(this.pageNumber);
        this.getProvidersByPageNumAndState(this.pageNumber);
        this.createModal.hide();
        setTimeout(() => this.active = true, 0);
    }

    closeCreateModal() {
        console.log('closing create modal');
        this.createModal.hide();
    }
    openCreateModal() {
        this.createModal.show();
    }

    getProvidersByPageNumAndState(pageNumber:number){
        console.log("getProvidersByPageNum "+ pageNumber + "; only active=" + this.onlyActive);
        this.pageNumber = +pageNumber;
        this.emptyArray();
        return this._providerService.getProvidersByState(this.pageNumber, this.onlyActive)
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

    onOnlyActive(){
        this.onlyActive = !this.onlyActive;
        console.log("change active filter, onlyActive=", this.onlyActive);
        if (this.onlyActive == true) {console.log("listing only active providers");
        } else {console.log("listing all providers");}
        this.getProvidersByPageNumAndState(this.pageNumber);
    }
}

