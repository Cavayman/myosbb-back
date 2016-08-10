/**
 * Created by Anastasiia Fedorak  8/8/16.
 */
import {Component} from "@angular/core";
import {ActivatedRoute} from "@angular/router";
import {ProviderService} from "./service/provider-service";
import {Provider} from "../../../shared/models/provider.interface";
import {CapitalizeFirstLetterPipe} from "../../../shared/pipes/capitalize-first-letter";
import {TranslatePipe} from "ng2-translate/ng2-translate";

@Component({
    selector: 'provider-info',
    templateUrl: 'src/app/user/provider/provider-info.html',
    providers: [ProviderService],
    pipes: [TranslatePipe, CapitalizeFirstLetterPipe],
    directives: []
})
export class ProviderInfoComponent {
    private sub: any;
    private provider : Provider =  {providerId:null, name:'', description:'', logoUrl:'', periodicity:'', type:null, email:'',phone:'', address:''};

    constructor(private route:ActivatedRoute, private _providerService:ProviderService){
        this.sub = this.route.params.subscribe(params => {
            let id = +params['id'];
            this._providerService.getProviderById(id)
                .subscribe(item=>(this.provider=item));
        });
    }
}