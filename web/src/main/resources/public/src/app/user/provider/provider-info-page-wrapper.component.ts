/**
 * Created by Anastasiia Fedorak  8/31/16.
 */
import {Component} from "@angular/core";
import {ProviderInfoComponent} from "./provider-info";

@Component({
    selector: 'provider-info-wrapper',
    template: `
                    <div class="row sb-backdown" >
                        <provider-info></provider-info>
                    </div>
    `,
    directives: [ProviderInfoComponent],
    styleUrls: ['src/app/house/house.css', 'src/shared/css/loader.css', 'src/shared/css/general.css']
})
export class ProviderInfoPageWrapperComponent {

}