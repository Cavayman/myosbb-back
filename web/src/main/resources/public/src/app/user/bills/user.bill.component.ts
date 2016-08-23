import {Component, OnInit} from "@angular/core";
import {FileDownloaderComponent} from "../../download/file.downloader.component";
import {BS_VIEW_PROVIDERS, MODAL_DIRECTIVES, BUTTON_DIRECTIVES} from "ng2-bootstrap";
import {CORE_DIRECTIVES, FORM_DIRECTIVES} from "@angular/common";
import {DateTimePickerDirective} from "ng2-datetime-picker/dist/datetime-picker.directive";
import {TranslatePipe} from "ng2-translate";
import {CapitalizeFirstLetterPipe} from "../../../shared/pipes/capitalize-first-letter";
import {Bill} from "./bill.interface";
import {PageCreator} from "../../../shared/services/page.creator.interface";
import {User} from "../../../shared/models/User";
import {CurrentUserService} from "../../../shared/services/current.user.service";
import {BillService} from "./bill.service";
import {ToasterContainerComponent, ToasterService} from "angular2-toaster/angular2-toaster";
import {
    onErrorServerNoResponseToastMsg,
    onErrorResourceNotFoundToastMsg
} from "../../../shared/error/error.handler.component";
import {SearchDTO} from "../../../shared/models/search.model";
import {HeaderComponent} from "../../header/header.component";

@Component({
    selector: 'my-user-bill',
    templateUrl: 'src/app/user/bills/bill.html',
    providers: [BillService, ToasterService, CurrentUserService],
    styleUrls: ['src/app/user/bills/bill.css', 'src/shared/css/loader.css', 'src/shared/css/general.css'],
    directives: [ToasterContainerComponent, FileDownloaderComponent, MODAL_DIRECTIVES, CORE_DIRECTIVES, FORM_DIRECTIVES, BUTTON_DIRECTIVES,
        DateTimePickerDirective],
    viewProviders: [BS_VIEW_PROVIDERS],
    pipes: [TranslatePipe, CapitalizeFirstLetterPipe]
})
export class UserBillComponent implements OnInit {


    private bills: Bill[] = [];
    private selectedBill: Bill = {};
    private pageCreator: PageCreator<Bill>;
    private pageNumber: number = 1;
    private pageList: Array<number> = [];
    private totalPages: number;
    private active: boolean = true;
    private order: boolean = true;
    private pending = false;
    private billId: number;
    private onSearch: boolean = false;
    private rows: number[] = [10, 20, 50];
    private selectedRow: number = 10;
    private searchDTO: SearchDTO = {pageNumber: 1, sortedBy: null, orderType: true, rowNum: 10};
    private currentUser: User;

    private options: {} = {ALL: 'ALL', PAID: 'PAID', NOT_PAID: 'NOT_PAID'};
    private status: string = this.options.ALL;

    constructor(private _billService: BillService, private _toasterService: ToasterService) {
        this.currentUser = HeaderComponent.currentUserService.getUser();
    }


    ngOnInit(): any {
        console.log('current user: ', this.currentUser.lastName);
        this.getBillsByPageNum(this.pageNumber, this.selectedRow);
    }


    refresh() {
        this.getBillsByPageNum(this.pageNumber, this.searchDTO.rowNum);
    }

    getBillsByPageNum(pageNumber: number, selectedRow: number, status: string) {
        this.pending = true;
        this.searchDTO.pageNumber = pageNumber;
        this.searchDTO.rowNum = selectedRow;
        this.status = status;
        return this._billService.getAllUserBills(this.currentUser.userId, this.searchDTO, this.status)
            .subscribe((data) => {
                    this.pending = false;
                    this.pageCreator = data;
                    this.bills = data.rows;
                    this.preparePageList(+this.pageCreator.beginPage,
                        +this.pageCreator.endPage);
                    this.totalPages = +data.totalPages;
                },
                (error) => {
                    this.pending = false;
                    this.handleErrors(error);
                });
    };

    private handleErrors(error) {
        if (error.status === 404 || error.status === 400) {
            console.log('server error 400');
            this._toasterService.pop(onErrorResourceNotFoundToastMsg);
            return;
        }

        if (error.status === 500) {
            console.log('server error 500');
            this._toasterService.pop(onErrorServerNoResponseToastMsg);
            return;
        }
    }


    isPaid(status: string): boolean {
        if (status == 'PAID') {
            return true;
        } else {
            return false;
        }
    }


    selectRowNum(row: number) {
        this.getBillsByPageNum(this.searchDTO.pageNumber, row, this.status);
    }


    prevPage() {
        this.searchDTO.pageNumber = this.searchDTO.pageNumber - 1;
        this.getBillsByPageNum(this.searchDTO.pageNumber, this.selectedRow, this.status);
    }

    nextPage() {
        this.searchDTO.pageNumber = this.searchDTO.pageNumber + 1;
        this.getBillsByPageNum(this.searchDTO.pageNumber, this.selectedRow, this.status);
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
        this.searchDTO.orderType = !this.searchDTO.orderType;
        this.searchDTO.sortedBy = name;
        console.log('order by asc', this.searchDTO.orderType);
        this._billService.getAllUserBills(this.currentUser.userId, this.searchDTO, this.status)
            .subscribe((data) => {
                    this.pageCreator = data;
                    this.bills = data.rows;
                    this.preparePageList(+this.pageCreator.beginPage,
                        +this.pageCreator.endPage);
                    this.totalPages = +data.totalPages;
                },
                (error) => {
                    this.handleErrors(error)
                });
    }


    onClickSearchByParam(value: string) {
        if (value.trim().length) {
            console.log('search by ', value);
            // this._reportService.searchUserReportsByInputParam(this.currentUser.userId, value)
            //     .subscribe((data)=> {
            //             this.onSearch = true;
            //             this.reports = data;
            //             this.preparePageList(this.pageNumber, this.pageNumber);
            //         },
            //         (error)=> {
            //             console.error(error)
            //         });
        }
    }

    processOption(status: string) {
        console.log('status', status);
        this.status = status;
        this.getBillsByPageNum(this.pageNumber, this.selectedRow, this.status);
    }

}


