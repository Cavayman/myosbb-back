import {Component, OnInit, OnDestroy, ViewChild} from "@angular/core";
import {CORE_DIRECTIVES} from "@angular/common";
import {Report} from "./report.interface";
import {ReportService} from "./report.service";
import {PageCreator} from "../../../shared/services/page.creator.interface";
import "rxjs/Rx";
import {MODAL_DIRECTIVES, BS_VIEW_PROVIDERS, ModalDirective, BUTTON_DIRECTIVES} from "ng2-bootstrap/ng2-bootstrap";
import {ReportFilter} from "./report.filter";
import {DomSanitizationService} from "@angular/platform-browser";
import {SELECT_DIRECTIVES} from "ng2-select";
import {TranslatePipe} from "ng2-translate";
import {CapitalizeFirstLetterPipe} from "../../../shared/pipes/capitalize-first-letter";
import {DateTimePickerDirective} from "ng2-datetime-picker/dist/datetime-picker.directive";
import {User} from "../../../shared/models/User";
import {HeaderComponent} from "../../header/header.component";
import {FORM_DIRECTIVES} from "@angular/forms";
import {FileDownloaderService} from "./download/report.downloader.service";
import FileServer = require("../../../shared/services/file.server.path");
import Regex = require('../../../shared/services/regex.all.text');


@Component({
    selector: 'my-report',
    templateUrl: 'src/app/user/report/report.html',
    providers: [ReportService, FileDownloaderService],
    directives: [MODAL_DIRECTIVES, CORE_DIRECTIVES, SELECT_DIRECTIVES, FORM_DIRECTIVES, BUTTON_DIRECTIVES, DateTimePickerDirective],
    viewProviders: [BS_VIEW_PROVIDERS],
    styleUrls: ['src/app/user/report/report.css', 'src/shared/css/loader.css', 'src/shared/css/general.css'],
    pipes: [ReportFilter, TranslatePipe, CapitalizeFirstLetterPipe]
})
export class UserReportComponent implements OnInit, OnDestroy {

    private reports: Report[] = [];
    private selectedReport: Report = {
        reportId: null,
        name: '',
        description: '',
        creationDate: '',
        filePath: '',
        userId: null
    };
    private pageCreator: PageCreator<Report>;
    private pageNumber: number = 1;
    private pageList: Array<number> = [];
    private totalPages: number;
    private dates: string[] = [];
    private dateFrom: string;
    private dateTo: string;
    @ViewChild('delModal') public delModal: ModalDirective;
    @ViewChild('editModal') public editModal: ModalDirective;
    @ViewChild('searchOptional') public searchOptional: ModalDirective;
    private active: boolean = true;
    private order: boolean = true;
    private isShowOptional: boolean = false;
    private dateFromActive: boolean;
    private dateToActive: boolean;
    private pending = false;
    private reportId: number;
    private onSearch: boolean = false;
    private rows: number[] = [10, 20, 50];
    private selectedRow: number = 10;
    private currentUser: User;
    private fileServerPath: string = FileServer.fileServerPath;

    constructor(private _reportService: ReportService,
                private sanitizer: DomSanitizationService,
                private _fileDownloaderservice: FileDownloaderService) {
        this.currentUser = HeaderComponent.currentUserService.getUser();
    }

    onClickShowOptional() {
        this.dateToActive = false;
        this.dateToActive = false;
        this.searchOptional.show();
    }

    openEditModal(report: Report) {
        this.selectedReport = report;
        console.log('selected report: ' + this.selectedReport);
        this.editModal.show();
    }

    isDateValid(date: string): boolean {
        return /\d{4}-\d{2}-\d{2}/.test(date);
    }

    onEditReportSubmit() {
        console.log('saving report: ' + this.selectedReport);
        this.editModal.hide();
        this._reportService.editAndSave(this.selectedReport)
            .subscribe(()=> {
                    this.refresh();
                },
                (error)=> {
                    console.log(error);
                });

    }

    closeEditModal() {
        this.active = false;
        console.log('closing edt modal');
        this.editModal.hide();
        setTimeout(()=> {
            this.active = true;
        }, 0);
    }

    openDelModal(id: number) {
        this.reportId = id;
        console.log('show', this.reportId);
        this.delModal.show();
    }

    closeDelModal() {
        console.log('delete', this.reportId);
        this.delModal.hide();
        this._reportService.deleteReportById(this.reportId)
            .subscribe(()=> {
                this.refresh();
            }, (error)=> {
                console.log(error);
            });
    }

    ngOnInit(): any {
        console.log('current user: ', this.currentUser.lastName);
        this.getReportsByPageNum(this.pageNumber, this.selectedRow);
    }


    refresh() {
        console.log('refreshing...');
        this.getReportsByPageNum(this.pageNumber, this.selectedRow);
    }

    getReportsByPageNum(pageNumber: number, selectedRow: number) {
        this.pageNumber = +pageNumber;
        this.pending = true;
        this.selectedRow = +selectedRow;
        return this._reportService.getAllUserReports(this.currentUser.userId,
            this.pageNumber, this.selectedRow)
            .subscribe((data) => {
                    this.pending = false;
                    this.pageCreator = data;
                    this.reports = data.rows;
                    this.preparePageList(+this.pageCreator.beginPage,
                        +this.pageCreator.endPage);
                    this.totalPages = +data.totalPages;
                    this.dates = data.dates;
                },
                (error) => {
                    this.pending = false;
                    console.error(error)
                });
    };


    selectRowNum(row: number) {
        this.getReportsByPageNum(this.pageNumber, row);
    }


    sanitizeUrlData(reports: Report[]): Report[] {
        let safeUrlReports = [];

        for (var report of reports) {
            report.filePath = this.sanitizer.bypassSecurityTrustUrl(report.filePath).toString();
            safeUrlReports.push(report);
        }

        return safeUrlReports;
    }


    prevPage() {
        this.pageNumber = this.pageNumber - 1;
        this.getReportsByPageNum(this.pageNumber, this.selectedRow);
    }

    nextPage() {
        this.pageNumber = this.pageNumber + 1;
        this.getReportsByPageNum(this.pageNumber, this.selectedRow);
    }

    emptyArray() {
        while (this.pageList.length) {
            this.pageList.pop();
        }
    }

    preparePageList(start: number, end: number) {
        this.emptyArray();
        for (let i = start; i <= end; i++) {
            this.pageList.push(i);
        }
    }


    sortBy(name: string) {
        console.log('sorted by ', name);
        this.order = !this.order;
        console.log('order by asc', this.order);
        this._reportService.getAllUserReportsSorted(this.currentUser.userId, this.pageNumber, name, this.order)
            .subscribe((data) => {
                    this.pageCreator = data;
                    this.reports = data.rows;
                    this.preparePageList(+this.pageCreator.beginPage,
                        +this.pageCreator.endPage);
                    this.totalPages = +data.totalPages;
                },
                (error) => {
                    console.error(error)
                });
    }


    ngOnDestroy(): any {
        //this.subscriber.unsubscribe();
    }

    private getDistinctDates(): string[] {
        let distinctDates = [];
        for (let report of this.reports) {
            if (distinctDates.indexOf(report.creationDate) < 0)
                distinctDates.push(report.creationDate);
        }
        return distinctDates;

    }


    refreshDateFrom(value: any) {
        console.log('date from', value);
        this.dateFrom = value.text;
        this.dateFromActive = false;
    }

    selectedDateFrom(value: any) {
        console.log('selected date from', value);
        this.dateFromActive = true;
    }

    refreshDateTo(value: any) {
        console.log('date to', value);
        this.dateTo = value.text;
        this.dateToActive = false;
    }

    selectedDateTo(value: any) {
        console.log('selected date to', value);
        this.dateToActive = true;
    }

    onClickSearchByDates() {
        this.searchOptional.hide();
        if (this.dateTo && this.dateFrom) {
            this._reportService.searchUserReportsByDates(this.currentUser.userId, this.dateFrom, this.dateTo)
                .subscribe((data)=> {
                        this.onSearch = true;
                        this.reports = data;
                        this.preparePageList(this.pageNumber, this.pageNumber);
                    },
                    (error)=> {
                        console.log(error)
                    })
        }


    }

    onClickSearchByParam(value: string) {
        if (value.trim().length && Regex.TEXT.test(value)) {
            console.log('search by ', value);
            this._reportService.searchUserReportsByInputParam(this.currentUser.userId, value)
                .subscribe((data)=> {
                        this.onSearch = true;
                        this.reports = data;
                        this.preparePageList(this.pageNumber, this.pageNumber);
                    },
                    (error)=> {
                        console.error(error)
                    });
        }
    }

    matches(value: string): boolean {
        /* text matching cyrillic alphabet also */
        if (Regex.TEXT.test(value)) {
            return true;
        }
        return false;
    }

    download(report: Report) {
        let id = report.reportId;
        console.log('id: ' + id);
        let filePath = report.filePath;
        let docType = filePath.substring(filePath.lastIndexOf('.') + 1);
        console.log('docType: ' + docType);
        this._fileDownloaderservice.downloadBy(id, docType);

    }
}