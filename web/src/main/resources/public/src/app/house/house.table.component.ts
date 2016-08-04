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
    private pageNumber = 1;
    private totalPages: string;
    private pageList: number[] = [];

    constructor(private _houseService: HouseService) {
    }

    ngOnInit(): any {
        this.findAllHousesByPage(this.pageNumber);

    }

    private findAllHousesByPage(pageNumber) {
        this.emptyPageList();
        this.pageNumber = +pageNumber;
        this._houseService.getAllHousesByPageNumber(this.pageNumber)
            .subscribe((data)=> {
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
        this.findAllHousesByPage(this.pageNumber)
    }

    nextPage() {
        this.pageNumber = this.pageNumber + 1;
        this.findAllHousesByPage(this.pageNumber)
    }


}