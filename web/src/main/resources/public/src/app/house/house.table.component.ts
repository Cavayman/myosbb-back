import {Component, OnInit, ViewChild} from "@angular/core";
import {HousePageObject} from "./house.page.object";
import {HouseService} from "./house.service";
import {Router} from "@angular/router";
import {TranslatePipe} from "ng2-translate";
import {CapitalizeFirstLetterPipe} from "../../shared/pipes/capitalize-first-letter";
import {ToasterContainerComponent, ToasterService} from "angular2-toaster/angular2-toaster";
import {
    onErrorResourceNotFoundToastMsg,
    onErrorServerNoResponseToastMsg
} from "../../shared/error/error.handler.component";
import {MODAL_DIRECTIVES, BS_VIEW_PROVIDERS, ModalDirective} from "ng2-bootstrap";
import {CORE_DIRECTIVES, FORM_DIRECTIVES} from "@angular/common";

@Component({
    selector: 'house-table',
    templateUrl: 'src/app/house/house_table.html',
    providers: [HouseService, ToasterService],
    directives: [ToasterContainerComponent, MODAL_DIRECTIVES, CORE_DIRECTIVES, FORM_DIRECTIVES],
    viewProviders: [BS_VIEW_PROVIDERS],
    styleUrls: ['src/app/house/house.css', 'src/shared/css/loader.css', 'src/shared/css/general.css'],
    pipes: [TranslatePipe, CapitalizeFirstLetterPipe]
})
export class HouseTableComponent implements OnInit {

    private houses: HousePageObject[] = [];
    private houseId: number;
    private pageNumber: number = 1;
    private totalPages: string;
    private pageList: Array<number> = [];
    private pending: boolean = false;
    private rows: number[] = [10, 20, 50];
    private selectedRow: number = 10;
    private onSearch: boolean = false;
    private selectedHouse: HousePageObject = {
        houseId: null, city: '', street: '', zipCode: '', description: '',
        osbbName: '', apartmentCount: null, numberOfInhabitants: null
    };
    private active: boolean = false;
    @ViewChild('delModal') private delModal: ModalDirective;
    @ViewChild('addModal') private addModal: ModalDirective;

    constructor(private _houseService: HouseService,
                private _router: Router,
                private _toasterService: ToasterService) {
    }

    ngOnInit(): any {
        this.findAllHousesByPage(this.pageNumber, this.selectedRow);

    }

    refresh() {
        this.findAllHousesByPage(this.pageNumber, this.selectedRow);
    }


    openDelModal(houseId: number) {
        this.houseId = houseId;
        this.delModal.show();
    }

    closeDelModal() {
        console.log("delete: " + this.houseId);
        this._houseService.deleteHouseById(this.houseId)
            .subscribe(() => {
                    console.log("refreshing...");
                    this.refresh()
                    this.delModal.hide();
                },
                (error)=> {
                    this.handleErrors(error)
                }
            );

    }

    showAddHouseModal() {
        console.log('opening addModal');
        this.active = true;
        this.addModal.show();
    }

    onAddHouseSubmit() {
        this.addModal.hide();
        console.log('saving ', this.selectedHouse);
        this.active = false;
        setTimeout(()=> {
            this.active = true
        }, 0);
    }

    matches(value: string): boolean {
        if (/^[a-zA-Z]+$/.test(value)) {
            return true;
        }
        return false;
    }

    private findAllHousesByPage(pageNumber, selectedRow) {
        this.emptyPageList();
        this.pending = true;
        this.pageNumber = +pageNumber;
        this.selectedRow = +selectedRow;
        this._houseService.getAllHousesByPageNumber(this.pageNumber, this.selectedRow)
            .subscribe((data)=> {
                    this.pending = false;
                    this.houses = data.rows;
                    this.totalPages = data.totalPages;
                    this.fillPageList(+data.beginPage, +data.endPage)
                },
                (error)=> {
                    this.handleErrors(error);
                });
    }

    fillPageList(beginIndex, endIndex) {
        for (let pageNum = beginIndex; pageNum <= endIndex; pageNum++) {
            this.pageList.push(pageNum);
        }
    }

    emptyPageList() {
        while (this.pageList.length) {
            this.pageList.pop();
        }
    }


    prevPage() {
        this.pageNumber = this.pageNumber - 1;
        this.findAllHousesByPage(this.pageNumber, this.selectedRow)
    }

    nextPage() {
        this.pageNumber = this.pageNumber + 1;
        this.findAllHousesByPage(this.pageNumber, this.selectedRow)
    }


    selectRowNum(row: number) {
        console.log('row number', row);
        this.findAllHousesByPage(this.pageNumber, row);
    }


    onClickSearchByParam(value: string) {
        if (value.trim().length) {
            this.emptyPageList();
            this.pending = true;
            console.log('search by ', value);
            this._houseService.searchByInputParam(value)
                .subscribe((data)=> {
                        this.pending = false;
                        this.onSearch = true;
                        this.houses = data;
                        this.fillPageList(this.pageNumber, this.pageNumber);
                    },
                    (error)=> {
                        this.handleErrors(error);
                    });
        }


    }

    private handleErrors(error: any) {
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

    onNavigate(id: number) {
        console.log('navigating to house id ', id);
        this._router.navigate(['home/house', id]);
    }


}