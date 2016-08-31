import {IApartment} from './src/shared/model/apartment.interface';
import {HousePageObject} from '../../house/house.page.object';
export class ApartmentModel implements IApartment{

    apartmentId:number;

    square:number;

    number:number;

    house:HousePageObject;

    owner:number;

    users:any[];
    bills:any[];

    constructor(apartmentId:number,square:number,number:number,house:HousePageObject,owner:number,
        users:any[],bills:any[]) {

        this.apartmentId=apartmentId;

       this.square=square;

        this.number=number;

        this.house=house;

        this.owner=owner;

        this.users=users;
       this. bills=bills;

    }


}

