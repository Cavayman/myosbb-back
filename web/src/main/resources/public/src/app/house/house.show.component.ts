import {Component, OnInit} from "@angular/core";
import {HouseService} from "./house.service";
import {HousePageObject} from "./house.page.object";
import {HeaderComponent} from "../header/header.component";


@Component({
    selector: 'my-user-house',
    templateUrl: 'src/app/house/house.html',
    providers: [HouseService],
    directives: [HeaderComponent]
})
export class HouseShowComponent implements OnInit {

    private housePageObject: HousePageObject;
    private houseId: number = 50;

    constructor(private _houseService: HouseService) {
        this.housePageObject = new HousePageObject();
    }


    ngOnInit(): any {
        this._houseService.getHouseById(this.houseId)
            .subscribe((data) => this.housePageObject = data,
                (error) => console.error(error));
    }


}