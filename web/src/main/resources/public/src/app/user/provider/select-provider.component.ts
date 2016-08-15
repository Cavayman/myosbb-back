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
                                <div style="width: 270px; margin-bottom: 20px;">
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

    @Input() selected : Provider =  {providerId:null, name:'Choose company', description:'', logoUrl:'', periodicity:'', type:null, email:'',phone:'', address:''};
    private providers: Provider[] = [];
    private items: Array<SelectItem>=new Array<SelectItem>();

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
                    this.items.push({id: this.providers[i].providerId, text: this.providers[i].name});
                }
                console.log("items: ", this.items);
            });
    }

    onSelect(value:SelectItem):void {
        this.selected.providerId = value.id;
        this.selected.name = value.text;
        console.log("seleced company: ", this.selected);
        this.providerChanged.emit(this.selected);
    }

    public onRemove(value:SelectItem):void {
        console.log('Removed value is: ', value);
    }

    public onType(value:SelectItem):void {
        console.log('New search input: ', value);
    }

    public onRefresh(value:SelectItem):void {
        this.selected.providerId = value.id;
        this.selected.name = value.text;
    }
}