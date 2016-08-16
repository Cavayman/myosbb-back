/**
 * Created by Anastasiia Fedorak  8/12/16.
 */
import {Component, EventEmitter, Output, Input} from "@angular/core";
import {DROPDOWN_DIRECTIVES} from "ng2-bs-dropdown/dropdown";

@Component({
    selector: 'currency',
    template: `
        <div class="dropdown">
            <span class=" dropdown-toggle" id="dropdownCurrency"> {{currentCurrency}}
            </span>
            <div class="dropdown-menu" aria-labelledby="dropdownCurrency">
                <a style="padding: 5px" class="dropdown-item" *ngFor="let cur of currencies" (click)="changeCurrency(cur)" >{{ cur }} |</a>
            </div>
        </div>
    `,
    directives: [DROPDOWN_DIRECTIVES]
})
export class CurrencyComponent {
    @Input() currentCurrency = '';
    private currencies = CURRENCIES;

    @Output() currencyChanged = new EventEmitter<string>();

    ngOnInit(){
        this.currentCurrency = this.currencies[0];
    }

    changeCurrency(currency: string){
        console.log("clicked: " + currency);
        this.currentCurrency = currency;
        this.currencyChanged.emit(currency);
    }
}

export const CURRENCIES = [
    "UAH",
    "USD",
    "EUR"
];