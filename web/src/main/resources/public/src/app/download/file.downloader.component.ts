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
import ApiService = require("../../shared/services/api.service");

let reportDownloadUrl = ApiService.serverUrl + '/restful/report/download';

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

    constructor(private _toasterService: ToasterService) {
    }


    public download(docType: string) {

        let self = this;
        this.pending = true;
        let xhr = new XMLHttpRequest();
        let url = reportDownloadUrl + '?type=' + docType;
        xhr.open('GET', url, true);
        xhr.responseType = 'blob';
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
            this._toasterService.pop(onSuccessToastMsg);
        } else {
            this._toasterService.pop(onErrorResourceNotFoundToastMsg);
        }


    }

}

export let onSuccessToastMsg: Toast = {
    type: 'success',
    title: '',
    body: RedirectComponent,
    showCloseButton: true,
    bodyOutputType: BodyOutputType.Component
};

export let onErrorResourceNotFoundToastMsg: Toast = {
    type: 'error',
    title: '',
    body: '<h5>Виникла помилка під час завантаження документа</h5>',
    showCloseButton: true,
    bodyOutputType: BodyOutputType.TrustedHtml
};
export let onErrorServerNoResponseToastMsg: Toast = {
    type: 'error',
    title: '',
    body: '<h5>Сервер не відповідає</h5>',
    showCloseButton: true,
    bodyOutputType: BodyOutputType.TrustedHtml
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