/**
 * Created by Anastasiia Fedorak  8/11/16.
 */
import {Component, Input, Output, EventEmitter} from "@angular/core";
import {ProviderService} from "./service/provider-service";
import {Provider} from "../../../shared/models/provider.interface";
import {CapitalizeFirstLetterPipe} from "../../../shared/pipes/capitalize-first-letter";
import {TranslatePipe} from "ng2-translate/ng2-translate";
import {SelectItem} from "../../../shared/models/ng2-select-item.interface";
import {SELECT_DIRECTIVES} from "ng2-select/ng2-select";
import {FORM_DIRECTIVES, NgClass, CORE_DIRECTIVES} from "@angular/common";
import {BUTTON_DIRECTIVES} from "ng2-bootstrap/ng2-bootstrap";

@Component({
    selector: 'select-provider',
    template: `<div class="form-group">
                          <div>
                             <label> {{ 'company' | translate | capitalize }}</label>
                                <div *ngIf="items.length > 0">
                                <div>
                                      <ng-select [allowClear]="true"
                                                  [items]="items"
                                                  (data)="onRefresh($event)"
                                                  (selected)="onSelect($event)"
                                                  (removed)="onRemove($event)"
                                                  (typed)="onType($event)"
                                                  placeholder="{{selected.name}}">
                                      </ng-select>
                               </div>
                            </div>
                         </div>
                     </div> <!-- Company -->`,
    providers: [ProviderService],
    pipes: [TranslatePipe, CapitalizeFirstLetterPipe],
    directives: [SELECT_DIRECTIVES, NgClass, CORE_DIRECTIVES, FORM_DIRECTIVES, BUTTON_DIRECTIVES ]
})
export class SelectProviderComponent {

    @Input() selected : Provider =  {providerId:null, name:'Choose company', description:'', logoUrl:'', periodicity:'', type:null, email:'',phone:'', address:'', schedule:'', active: false};
    private providers: Provider[] = [];
    private items: Array<SelectProviderItem>=new Array<SelectProviderItem>();

    constructor(private _providerService:ProviderService){

    }

    @Output() providerChanged : EventEmitter<Provider>  = new EventEmitter<Provider>();

    constructor(private _providerService:ProviderService) {
        console.log("constructor provider type cmp");
    }

    ngOnInit() {
        this._providerService.getAllProviders()
            .subscribe((data) => {
                this.providers = data;
                for(let i = 0; i < this.providers.length; i++) {
                    this.items.push({id: this.providers[i].providerId, text: this.providers[i].name,
                        providerId:this.providers[i].providerId,
                        name: this.providers[i].name,
                        description: this.providers[i].description,
                        logoUrl: this.providers[i].logoUrl,
                        periodicity: this.providers[i].periodicity,
                        type:  this.providers[i].type,
                        email: this.providers[i].email,
                        phone: this.providers[i].phone,
                        address: this.providers[i].address,
                        schedule:  this.providers[i].schedule,
                        active:  this.providers[i].active
                        });
                }
                console.log("items: ", this.items);
            });
    }

    onSelect(value:SelectProviderItem):void {
        // var id : number = value.id;
        // this._providerService.getProviderById(id)
        //     .subscribe((provider)=>{
        //         this.selected = provider;
        //         console.log("find provider ", provider)
        //     });
            this.selected.providerId = value.id;
            this.selected.name = value.text;
            this.selected.description = value.description,
            this.selected.logoUrl = value.logoUrl,
            this.selected.periodicity = value.periodicity,
            this.selected.type = value.type,
            this.selected.email = value.email,
            this.selected.phone = value.phone,
            this.selected.address = value.address,
            this.selected.schedule = value.schedule,
            this.selected.active  = value.active
        console.log("selected company: ", this.selected.name);
        this.providerChanged.emit(this.selected);
    }

    public onRemove(value:SelectProviderItem):void {
        console.log('Removed value is: ', value);
    }

    public onType(value:SelectProviderItem):void {
        console.log('New search input: ', value);
    }

    public onRefresh(value:SelectProviderItem):void {
        this.selected.providerId = value.id;
        this.selected.name = value.text;
    }
}

export interface SelectProviderItem extends Provider, SelectItem{
}