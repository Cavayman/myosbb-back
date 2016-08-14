import {Component, OnInit} from "@angular/core";
import {HousePageObject} from "./house.page.object";
import {HouseService} from "./house.service";
import {Router} from "@angular/router";

@Component({
    selector: 'house-table',
    templateUrl: 'src/app/house/house_table.html',
    providers: [HouseService],
    styleUrls: ['src/app/house/house.css', 'src/shared/css/loader.css', 'src/shared/css/general.css']
})
export class HouseTableComponent implements OnInit {

    private houses: HousePageObject[] = [];
    private pageNumber: number = 1;
    private totalPages: string;
    private pageList: Array<number> = [];
    private pending: boolean = false;
    private rows: number[] = [10, 20, 50];
    private selectedRow: number = 10;
    private onSearch: boolean = false;

    constructor(private _houseService: HouseService, private _router: Router) {
    }

    ngOnInit(): any {
        this.findAllHousesByPage(this.pageNumber, this.selectedRow);

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
                    console.log(error)
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
                        console.error(error)
                    });
        }


    }

    onNavigate(id: number) {
        console.log('navigating to house id ', id);
        this._router.navigate(['house', id]);
    }


}