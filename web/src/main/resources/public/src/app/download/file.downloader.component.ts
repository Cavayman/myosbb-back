import {Component} from "@angular/core";
import {FileDownloaderService} from "./file.downloader.service";
import {
    ToasterContainerComponent,
    Toast,
    ToasterService,
    ToasterConfig,
    BodyOutputType
} from "angular2-toaster/angular2-toaster";
import {RedirectComponent} from "./redirect.component";
import {CurrentUserService} from "../../shared/services/current.user.service";
import {User} from "../../shared/models/User";
import {
    onErrorServerNoResponseToastMsg,
    onErrorResourceNotFoundToastMsg
} from "../../shared/error/error.handler.component";
import ApiService = require("../../shared/services/api.service");

let reportUrl = ApiService.serverUrl + '/restful/report/user/';

declare var saveAs: any;

const PDF_MIME_TYPE = 'application/pdf';
const EXCEL_MIME_TYPE = 'text/xls';
const CSV_MIME_TYPE = 'text/csv';


@Component({
    selector: 'file-downloader',
    templateUrl: 'src/app/download/file.downloader.html',
    providers: [FileDownloaderService, ToasterService],
    directives: [ToasterContainerComponent],
    styleUrls: ['src/shared/css/loader.css']
})
export class FileDownloaderComponent {

    private pending: boolean = false;
    private hasError: boolean = false;
    public toasterconfig: ToasterConfig =
        new ToasterConfig({timeout: 20000});
    private currentUser: User;

    constructor(private _toasterService: ToasterService, private _currentUserService: CurrentUserService) {
    }


    public download(docType: string) {
        this.currentUser = this._currentUserService.getUser();
        console.log('current user: ', this.currentUser.lastName);
        let self = this;
        this.pending = true;
        let xhr = new XMLHttpRequest();
        let url = reportUrl + this.currentUser.userId + '/download?type=' + docType;
        xhr.open('GET', url, true);
        xhr.responseType = 'blob';
        xhr.setRequestHeader('Authorization', 'Bearer ' + localStorage.getItem('token'));
        console.log('preparing download...');

        xhr.onreadystatechange = function () {

            if (xhr.readyState != 4) return;

            clearTimeout(serverTimeout);

            if (xhr.status === 200) {
                let mimeType = setContentType(docType);
                var blob = new Blob([this.response], {type: mimeType});
                saveAs(blob, setFileName(docType));
                self.pending = false;
                self.hasError = false;
                self.handleResponse();
            } else {
                console.error('error while loading resources');
                self.pending = false;
                self.hasError = true;
                self.handleResponse();

            }

        };

        xhr.send();

        let serverTimeout = setTimeout(()=> {
            console.log('terminating server connection d/t too long connection time');
            xhr.abort();
            this._toasterService.pop(onErrorServerNoResponseToastMsg);
        }, 15000)

    }

    private handleResponse() {
        if (!this.pending && !this.hasError) {
            this._toasterService.pop(onFileDownloadSuccessToastMsg);
        } else {
            this._toasterService.pop(onErrorResourceNotFoundToastMsg);
        }


    }

}

export let onFileDownloadSuccessToastMsg: Toast = {
    type: 'success',
    title: '',
    body: RedirectComponent,
    showCloseButton: true,
    bodyOutputType: BodyOutputType.Component
};


function setFileName(docType) {
    let fileName: string;
    console.log('setting filename: report.' + docType);
    switch (docType) {
        case 'pdf':
            fileName = 'report.pdf';
            break;
        case 'xls':
            fileName = 'report.xls';
            break;
        case 'csv':
            fileName = 'report.csv';
            break;
        default:
            fileName = 'report.txt';
    }

    return fileName;
}
function setContentType(type) {
    console.log('setting contentType: ' + type);
    let mimeType: string;
    switch (type) {
        case 'pdf':
            mimeType = PDF_MIME_TYPE;
            break;
        case 'xls':
            mimeType = EXCEL_MIME_TYPE;
            break;
        case 'csv':
            mimeType = CSV_MIME_TYPE;
            break;
    }
    return mimeType;
}