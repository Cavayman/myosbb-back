import {Component, OnInit, OnDestroy, ViewChild} from "@angular/core";
import {CORE_DIRECTIVES} from "@angular/common";
import {Report} from "./report.interface";
import {ReportService} from "./report.service";
import {PageCreator} from "../../../shared/services/page.creator.interface";
import "rxjs/Rx";
import {MODAL_DIRECTIVES, BS_VIEW_PROVIDERS, ModalDirective} from "ng2-bootstrap/ng2-bootstrap";

@Component({
    selector: 'my-report',
    templateUrl: 'src/app/user/report/report.html',
    providers: [ReportService],
    directives: [MODAL_DIRECTIVES, CORE_DIRECTIVES],
    viewProviders: [BS_VIEW_PROVIDERS]
})
export class UserReportComponent implements OnInit, OnDestroy {

    private reports:Report[];
    private selectedReport:Report = {reportId: null, name: '', description: '', creationDate: '', filePath: ''};
    private pageCreator:PageCreator<Report>;
    private pageNumber:number = 1;
    private pageList:Array<number> = [];
    private totalPages:number;
    @ViewChild('delModal') public delModal:ModalDirective;
    @ViewChild('editModal') public editModal:ModalDirective;
    active:boolean = true;
    order:boolean = true;

    private reportId:number;

    constructor(private _reportService:ReportService) {
    }

    openEditModal(report:Report) {
        this.selectedReport = report;
        console.log('selected report: ' + this.selectedReport);
        this.editModal.show();
    }

    isDateValid(date:string):boolean {
        return /\d{4}-\d{2}-\d{2}/.test(date);
    }

    onEditReportSubmit() {
        this.active = false;
        console.log('saving report: ' + this.selectedReport);
        this._reportService.editAndSave(this.selectedReport);
        this._reportService.getAllReports(this.pageNumber);
        this.editModal.hide();
        setTimeout(() => this.active = true, 0);
    }

    closeEditModal() {
        console.log('closing edt modal');
        this.editModal.hide();
    }

    openDelModal(id:number) {
        this.reportId = id;
        console.log('show', this.reportId);
        this.delModal.show();
    }

    closeDelModal() {
        console.log('delete', this.reportId);
        this._reportService.deleteReportById(this.reportId);
        this.getReportsByPageNum(this.pageNumber);
        this.delModal.hide();
    }

    ngOnInit():any {
        this.getReportsByPageNum(this.pageNumber);
    }

    getReportsByPageNum(pageNumber:number) {
        this.pageNumber = +pageNumber;
        this.emptyArray();
        return this._reportService.getAllReports(this.pageNumber)
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
    };

    prevPage() {
        this.pageNumber = this.pageNumber - 1;
        this.getReportsByPageNum(this.pageNumber)
    }

    nextPage() {
        this.pageNumber = this.pageNumber + 1;
        this.getReportsByPageNum(this.pageNumber)
    }

    emptyArray() {
        while (this.pageList.length) {
            this.pageList.pop();
        }
    }

    preparePageList(start:number, end:number) {
        for (let i = start; i <= end; i++) {
            this.pageList.push(i);
        }
    }


    sortBy(name:string) {
        console.log('sorted by ', name);
        this.order = !this.order;
        console.log('order by asc', this.order);
        this.emptyArray();
        this._reportService.getAllReportsSorted(this.pageNumber, name, this.order)
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


    ngOnDestroy():any {
        //this.subscriber.unsubscribe();
    }

}