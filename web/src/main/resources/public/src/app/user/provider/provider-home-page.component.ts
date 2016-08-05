/**
 * Created by Anastasiia Fedorak  8/3/16.
 */
import {Component} from "@angular/core";
import {RouteParams} from "@angular/router-deprecated";

@Component({
    selector: '',
    template: `
        <div>
        
        </div>
    `,
    directives: []
})
export class ProviderHomeComponent {
        private id: string;

    constructor(private _params:RouteParams)
    {
        this.id=_params.get('id');
    }


}