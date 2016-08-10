/**
 * Created by Anastasiia Fedorak  8/4/16.
 */
import {Component, OnInit, Output} from "@angular/core";
import {ProviderTypeService} from "./service/provider-type.service";
import {DROPDOWN_DIRECTIVES} from "ng2-bootstrap/ng2-bootstrap";
import {ProviderService} from "../service/provider-service";
import {TranslatePipe} from "ng2-translate/ng2-translate";
import {EventEmitter} from "@angular/core";
import {ProviderType} from "../../../../shared/models/provider.type.interface";
import {CapitalizeFirstLetterPipe} from "../../../../shared/pipes/capitalize-first-letter";
import {Http} from "@angular/http";

@Component({
    selector: 'provider-type',
    template: `
        <div>
        
         <label> {{ 'type' | translate:{value:param} | capitalize }}</label>
                <div class="form-control-type">
                    <div class="dropdown">
                        <button class="btn btn-default dropdown-toggle" type="button" id="dropdownProviderType"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                            <span class="caret"></span>{{selected.providerTypeName}}
                        </button>
                        <ul class="dropdown-menu" aria-labelledby="dropdownProviderType">
                            <li *ngFor="let type of types"><a style="cursor: pointer"  (click)="onSelect(type)" >{{type.providerTypeName}}</a></li>
                        </ul>
                    </div>
                </div>
        </div> <!-- Type -->
    `,
    providers: [ProviderTypeService],
    pipes: [TranslatePipe, CapitalizeFirstLetterPipe],
    directives: [DROPDOWN_DIRECTIVES],
   // outputs: ['typeChanged']
})
export class ProviderTypeComponent implements OnInit {
    private types:ProviderType[] ;
    private selected : ProviderType = {providerTypeId: null, providerTypeName: ''};

    @Output() typeChanged : EventEmitter<ProviderType>;

    ngOnInit() {
        console.log("init method");
        this._providerTypeService.getProviderTypes()
            .subscribe((data) => {
                console.log("DATA: " + data);
                this.types = data;
                console.log("GET TYPES " + this.types[0].providerTypeName);
                this.selected = this.types[0];
                console.log(this.selected.providerTypeName)
            });
    }

    onSelect(type: ProviderType){
        this.selected = type;
        console.log("select type " + type.providerTypeName);
        this.typeChanged.emit(type);
    }

    constructor(private _providerTypeService:ProviderTypeService) {
        console.log("constructor provider type cmp");
        this.typeChanged = new EventEmitter<ProviderType>();
        console.log("create event emitter");
    }
}
