/**
 * Created by Anastasiia Fedorak  8/4/16.
 */
import {Component, OnInit, Output, EventEmitter, Input} from "@angular/core";
import {CORE_DIRECTIVES, NgClass, FORM_DIRECTIVES} from "@angular/common";
import {ProviderTypeService} from "./service/provider-type.service";
import {ProviderType} from "../../../../shared/models/provider.type.interface";
import {DROPDOWN_DIRECTIVES, BUTTON_DIRECTIVES} from "ng2-bootstrap/ng2-bootstrap";
import {TranslatePipe} from "ng2-translate/ng2-translate";
import {CapitalizeFirstLetterPipe} from "../../../../shared/pipes/capitalize-first-letter";
import {SELECT_DIRECTIVES} from "ng2-select/ng2-select";
import {CORE_DIRECTIVES, FORM_DIRECTIVES, NgClass} from '@angular/common';
import {BUTTON_DIRECTIVES } from 'ng2-bootstrap/ng2-bootstrap';
import {SelectItem} from "../../../../shared/models/ng2-select-item.interface";

@Component({
    selector: 'provider-type',
    template: `
        <div>            
         <label> {{ 'type' | translate:{value:param} | capitalize }}</label>
            <div *ngIf="items.length > 0">
                            <div style="width: 400px; margin-bottom: 20px;">
                  <ng-select [allowClear]="true"
                              [items]="items"
                              (data)="onRefresh($event)"
                              (selected)="onSelect($event)"
                              (removed)="onRemove($event)"
                              (typed)="onType($event)"
                              placeholder="{{type.providerTypeName}}">
                  </ng-select>
                  <p></p>
                  </div>
                </div>
        </div> <!-- Type -->
    `,
    providers: [ProviderTypeService],
    pipes: [TranslatePipe, CapitalizeFirstLetterPipe],
    directives: [SELECT_DIRECTIVES, NgClass, CORE_DIRECTIVES, FORM_DIRECTIVES, BUTTON_DIRECTIVES ]

})
export class ProviderTypeComponent {
    private types : Array<ProviderType> = new Array<ProviderType>();
    public items: Array<SelectItem> = new Array<SelectItem>();
    private selected : ProviderType = {providerTypeId: null, providerTypeName: ''};
    @Input() type : ProviderType;
    @Output() typeChanged : EventEmitter<ProviderType>;

    constructor(private _providerTypeService:ProviderTypeService) {
        console.log("constructor provider type cmp");
        this.typeChanged = new EventEmitter<ProviderType>();
        console.log("create event emitter");
    }

    ngOnInit() {
        console.log("init method");
        this._providerTypeService.getProviderTypes()
            .subscribe((data) => {
                console.log("DATA: ", data);
                this.types = data;
                for(let i = 0; i < this.types.length; i++) {
                    this.items.push({id: this.types[i].providerTypeId, text: this.types[i].providerTypeName});
                }
                console.log("items: ", this.items);
            });
    }
    public onSelect(value:SelectItem):void {
       this.selected = {providerTypeId: value.id, providerTypeName: value.text};
       console.log("select type " + this.selected.providerTypeName);
       this.typeChanged.emit(this.selected);
        console.log('Selected value is: ', this.selected);
    }

    public onRemove(value:SelectItem):void {
        console.log('Removed value is: ', value);
    }

    public onType(value:SelectItem):void {
        console.log('New search input: ', value);
    }

    public onRefresh(value:SelectItem):void {
        this.selected = {providerTypeId: value.id, providerTypeName: value.text};
    }
}