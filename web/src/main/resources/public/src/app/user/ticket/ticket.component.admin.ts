import {Component} from "@angular/core";
import {TicketComponent} from "./ticket.component";

@Component({
    selector: 'ticket-admin',
    template: `
    <div class="row sb-backdown">
                <ticket>
                </ticket>       
    </div>
    `,
   styleUrls: ['src/app/house/house.css', 'src/shared/css/loader.css', 'src/shared/css/general.css'],
    directives: [TicketComponent]
})
export class TicketAdminComponent {

}