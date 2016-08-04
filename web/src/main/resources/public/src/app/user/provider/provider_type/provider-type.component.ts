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

@Component({
    selector: 'provider-type',
    template: `
        <div>
        
         <label> {{ 'type' | translate:{value:param} | capitalize }}</label>
                <div class="form-control-type">
                    <div class="dropdown">
                        <button class="btn btn-default dropdown-toggle" type="button" id="dropdownProviderType"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                            <span class="caret"></span>{{selected.type}}
                        </button>
                        <ul class="dropdown-menu" aria-labelledby="dropdownProviderType">
                            <li *ngFor="let type of types"><a style="cursor: pointer"  (click)="onSelect(type)" >{{type.type}}</a></li>
                        </ul>
                    </div>
                </div>
        </div> <!-- Type -->
    `,
    providers: [ProviderTypeService],
    pipes: [TranslatePipe, CapitalizeFirstLetterPipe],
    directives: [DROPDOWN_DIRECTIVES]
 //   outputs: ['typeChanged']
})
export class ProviderTypeComponent implements OnInit {
    private types:ProviderType[] = [];
    private selected : ProviderType = {id: 0, type: ''};

    @Output() typeChanged : EventEmitter<ProviderType>;

    ngOnInit() {

    }


    onSelect(type: ProviderType){
        this.selected = type;
        console.log("select type " + type.type);
        this.typeChanged.emit(type);
    }

    constructor(private _providerTypeService:ProviderTypeService) {
        console.log("init provider type cmp");
        this.typeChanged = new EventEmitter<ProviderType>();
        console.log("create event emitter");
        console.log("sending http GET");
        this._providerTypeService.getProviderTypes()
            .subscribe((data) => {
                this.types = data;
                console.log("GET TYPES " + this.types);
                    this.selected = this.types[0];
                        console.log(this.selected.type)
                                },
                        (err) => {
                             console.log(err)
                                });

        // this._providerTypeService.getProviderTypes().then(types =>
        // this.types = types);
        // console.log(this.types);
        // this.selected = this.types[0];
        // console.log("get types: " + this.types + "; first elem: " + this.selected);

        // this.types.push(TYPES[0]);
        // this.selected = TYPES[0];
    }
}


export const TYPES: ProviderType[] = [
    {id: 1, type: 'Inet'},
    {id: 2, type: 'Trash'},
    {id: 3, type: 'Other'},
    {id: 4, type: 'Another'},
    {id: 5, type: 'Lol'},
    {id: 6, type: 'Mr. Nice'},
    {id: 7, type: 'll'},
];