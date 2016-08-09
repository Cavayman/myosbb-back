import {Injectable} from "@angular/core";
import ApiService = require("../../shared/services/api.service");

let reportDownloadUrl = ApiService.serverUrl + '/restful/report/download';

declare var saveAs:any;

const PDF_MIME_TYPE = 'application/pdf';
const EXCEL_MIME_TYPE = 'text/xls';
const CSV_MIME_TYPE = 'text/csv';


@Injectable()
export class FileDownloaderService {

    download(docType:string, pending:boolean) {

        let self = this;

        pending = true;

        let xhr = new XMLHttpRequest();
        let url = reportDownloadUrl + '?type=' + docType;
        xhr.open('GET', url, true);
        xhr.responseType = 'blob';
        console.log('preparing download...');
        pending = false;
        xhr.onreadystatechange = function () {
            setTimeout(() => {
                pending = false;
                console.log('inside service: ' + pending);
            }, 0);

            if (xhr.readyState === 4 && xhr.status === 200) {
                let mimeType = setContentType(docType);
                var blob = new Blob([this.response], {type: mimeType});
                saveAs(blob, setFileName(docType));
            } else if (xhr.status === 404) {
                console.error('could not find resource');

            }
        };

        xhr.send();
    }


}

function setFileName(docType) {
    let fileName:string;
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
    let mimeType:string;
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
