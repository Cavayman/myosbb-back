import {Component} from "@angular/core";
import {FileDownloaderComponent} from "../../download/file.downloader.component";

@Component({
    selector: 'my-user-bill',
    templateUrl: 'src/app/user/bills/bill.html',
    directives: [FileDownloaderComponent],
    styleUrls: ['src/app/user/bills/bill.css']
})
export class UserBillComponent {


}