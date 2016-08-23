import {Component, OnInit, OnDestroy} from "@angular/core";
import {HouseService} from "./house.service";
import {HousePageObject} from "./house.page.object";
import {HeaderComponent} from "../header/header.component";
import {ActivatedRoute} from "@angular/router";
import {Subscription} from "rxjs";


@Component({
    selector: 'my-user-house',
    templateUrl: 'src/app/house/house.html',
    providers: [HouseService],
    directives: [HeaderComponent],
    styleUrls: ['src/app/house/house.css']
})
export class HouseShowComponent implements OnInit, OnDestroy {

    private housePageObject: HousePageObject;
    private houseId: number;
    private sub: Subscription;

    constructor(private _houseService: HouseService, private _routeParams: ActivatedRoute) {
        this.housePageObject = new HousePageObject();
    }


    ngOnInit(): any {
        this.sub = this._routeParams.params.subscribe((params)=> {
            this.houseId = +params['id'];
            this._houseService.getHouseById(this.houseId)
                .subscribe((data) => this.housePageObject = data,
                    (error) => console.error(error));
        })

    }


    ngOnDestroy(): any {
        if (this.sub)
            this.sub.unsubscribe();
    }
}