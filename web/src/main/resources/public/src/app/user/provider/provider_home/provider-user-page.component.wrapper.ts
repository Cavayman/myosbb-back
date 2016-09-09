/**
 * Created by Anastasiia Fedorak  8/31/16.
 */
import {Component} from "@angular/core";
import {ProviderUserPageComponent} from "./provider-user-page.component";

@Component({
    selector: 'provider-home-wrapper',
    template: `
    <div class="row sb-backdown">
                <provider-home>
                </provider-home>       
    </div>
    `,
    styleUrls: ['src/app/house/house.css', 'src/shared/css/loader.css', 'src/shared/css/general.css'],
    directives: [ProviderUserPageComponent]
})
export class ProviderUserPageWrapperComponent {

}