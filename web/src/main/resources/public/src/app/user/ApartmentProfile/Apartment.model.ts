export interface IApartmentModel{
    apartmentId:number;

    square:number;

    number:number;

    house:any;

    user:any;

    users:any[];
    bills:any[];
}



export class singleApartmentModel implements IApartmentModel{

    apartmentId:number;

    square:number;

    number:number;

    house:any;

    user:any;

    users:any[];
    bills:any[];

    constructor() {
        

    }


}

