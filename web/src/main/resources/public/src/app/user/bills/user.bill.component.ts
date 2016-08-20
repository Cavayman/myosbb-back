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

@Component({
    selector: 'my-user-bill',
    templateUrl: 'src/app/user/bills/bill.html',
    providers: [BillService],
    styleUrls: ['src/app/user/bills/bill.css', 'src/shared/css/loader.css', 'src/shared/css/general.css'],
    directives: [FileDownloaderComponent, MODAL_DIRECTIVES, CORE_DIRECTIVES, FORM_DIRECTIVES, BUTTON_DIRECTIVES,
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
    private currentUser: User;
    private apartmentId: number;

    constructor(private _currentUserService: CurrentUserService,
                private _billService: BillService) {
        this.currentUser = new User(this._currentUserService.getUser());
    }


    ngOnInit(): any {
        console.log('current user: ', this.currentUser.lastName);
        this.getBillsByPageNum(this.pageNumber, this.selectedRow);
    }


    refresh() {
        this.getBillsByPageNum(this.pageNumber, this.selectedRow);
    }

    getBillsByPageNum(pageNumber: number, selectedRow: number) {
        this.pageNumber = +pageNumber;
        this.pending = true;
        this.selectedRow = +selectedRow;
        return this._billService.getAllUserBills(this.currentUser.userId,
            this.pageNumber, this.selectedRow)
            .subscribe((data) => {
                    this.pending = false;
                    this.pageCreator = data;
                    this.bills = data.rows;
                    this.preparePageList(+this.pageCreator.beginPage,
                        +this.pageCreator.endPage);
                    this.totalPages = +data.totalPages;
                    this.apartmentId = data.apartmentId;
                },
                (error) => {
                    this.pending = false;
                    console.error(error)
                });
    };


    selectRowNum(row: number) {
        this.getBillsByPageNum(this.pageNumber, row);
    }


    prevPage() {
        this.pageNumber = this.pageNumber - 1;
        this.getBillsByPageNum(this.pageNumber, this.selectedRow);
    }

    nextPage() {
        this.pageNumber = this.pageNumber + 1;
        this.getBillsByPageNum(this.pageNumber, this.selectedRow);
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
        this._billService.getAllUserBillsSorted(this.currentUser.userId, this.pageNumber, name, this.order)
            .subscribe((data) => {
                    this.pageCreator = data;
                    this.bills = data.rows;
                    this.preparePageList(+this.pageCreator.beginPage,
                        +this.pageCreator.endPage);
                    this.totalPages = +data.totalPages;
                },
                (error) => {
                    console.error(error)
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

}