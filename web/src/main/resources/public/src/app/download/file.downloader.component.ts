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
    directives: [ToasterContainerComponent]
})
export class FileDownloaderComponent {

    private pending: boolean = false;
    private hasError: boolean = false;
    public toasterconfig: ToasterConfig =
        new ToasterConfig({timeout: 15000});

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
            setTimeout(() => {
                self.pending = false;
                console.log('inside service: ' + self.pending);
            }, 0);

            if (xhr.readyState === 4 && xhr.status === 200) {
                let mimeType = setContentType(docType);
                var blob = new Blob([this.response], {type: mimeType});
                saveAs(blob, setFileName(docType));
            } else if (xhr.status === 404) {
                console.error('could not find resource');
                self.pending = false;
                self.hasError = true;
                self._toasterService.pop(etoast);

            }
        };

        xhr.send();
        setTimeout(()=> {
            if ((!self.pending || self.pending) && !self.hasError)
                self._toasterService.pop(stoast);
            else {
                self._toasterService.pop(etoast);
            }
        }, 5000);


    }

}

export let stoast: Toast = {
    type: 'success',
    title: '',
    body: RedirectComponent,
    showCloseButton: true,
    bodyOutputType: BodyOutputType.Component
};

export let etoast: Toast = {
    type: 'error',
    title: '',
    body: '<h5>Виникла помилка під час завантаження документа</h5>',
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