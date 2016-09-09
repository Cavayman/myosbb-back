import {Component} from "@angular/core";
import {MessageComponent} from "./single.ticket.component";

@Component({
    selector: 'ticket-admin',
    template: `
    <div class="row sb-backdown">
                <ticket>
                </ticket>       
    </div>
    `,
   styleUrls: ['src/app/house/house.css', 'src/shared/css/loader.css', 'src/shared/css/general.css'],
    directives: [MessageComponent]
})
export class TicketSingleAdminComponent {

}
