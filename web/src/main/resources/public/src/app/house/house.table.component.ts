import {Component, OnInit} from "@angular/core";
import {HousePageObject} from "./house.page.object";
import {HouseService} from "./house.service";

@Component({
    selector: 'house-table',
    templateUrl: 'src/app/house/house_table.html',
    providers: [HouseService],
    styleUrls: ['src/app/house/house.css']
})
export class HouseTableComponent implements OnInit {

    private houses: HousePageObject[];
    private pageNumber: number = 1;
    private totalPages: string;
    private pageList: Array<number> = [];
    private pending: boolean = false;
    private rows: number[] = [10, 20, 50];
    private selectedRow: number = 10;

    constructor(private _houseService: HouseService) {
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


}