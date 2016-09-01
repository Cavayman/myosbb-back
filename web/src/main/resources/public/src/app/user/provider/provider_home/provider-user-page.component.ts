/**
 * Created by Anastasiia Fedorak  8/17/16.
 */
import {Component} from "@angular/core";
import {TranslatePipe} from "ng2-translate/ng2-translate";
import {CapitalizeFirstLetterPipe} from "../../../../shared/pipes/capitalize-first-letter";
import {Provider} from "../../../../shared/models/provider.interface";
import {PageCreator} from "../../../../shared/services/page.creator.interface";
import {SelectItem} from "../../../../shared/models/ng2-select-item.interface";
import {ProviderService} from "../service/provider-service";
import {PeriodicityItems} from "../../../../shared/models/periodicity.const";
import {ROUTER_DIRECTIVES} from "@angular/router";

@Component({
    selector: 'provider-home',
    templateUrl: 'src/app/user/provider/provider_home/provider-user-page.html',
    pipes: [TranslatePipe, CapitalizeFirstLetterPipe],
    providers: [ProviderService],
    directives: [ROUTER_DIRECTIVES],
    styleUrls: ['src/app/house/house.css', 'src/shared/css/loader.css', 'src/shared/css/general.css']
})
export class ProviderUserPageComponent {
    private providers :  Provider[];
    private pageCreator:PageCreator<Provider>;
    private pageNumber:number = 1;
    private pageList:Array<number> = [];
    private totalPages:number;
    onlyActive: boolean = true;
    private providerId:number;
    private periodicities: SelectItem[] = [];

    constructor(private _providerService:ProviderService){
    }

    ngOnInit():any {
        // console.log("init provider home cmp");
        // console.log("periodicity items:", PeriodicityItems);
        // for (let i=0; i<PeriodicityItems.length; i++){
        //     this.periodicities.push(PeriodicityItems[i]);
        // }
        // this.getPeriodicitiesTranslation();
        // console.log('readable periodicities: ', this.periodicities);
        this.getProvidersByPageNumAndState(this.pageNumber);
    }
    //
    // getPeriodicitiesTranslation(){
    //     console.log("got lang",  HeaderComponent.translateService.currentLang);
    //     for (let i=0; i < this.periodicities.length; i++){
    //         HeaderComponent.translateService.get(this.periodicities[i].text)
    //             .subscribe((data : string) => {
    //                 this.periodicities[i].text = data;
    //                 console.log("periodicity =", this.periodicities[i]);
    //             })
    //     }
    //     console.log("periodicities: ", this.periodicities);
    // }

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

}