export interface IApartmentModel{
    apartmentId:number;

    square:number;

    number:number;

    house:any;

    user:any;

    users:any[];
    bills:any[];
}



export class ApartmentModel implements IApartmentModel{

    apartmentId:number;

    square:number;

    number:number;

    house:any;

    user:any;

    users:any[];
    bills:any[];

    constructor(apartmentId:number,square:number,number:number,house:any,user:any,
        users:any[],bills:any[]) {

        this.apartmentId=apartmentId;

       this.square=square;

        this.number=number;

        this.house=house;

        this.user=user;

        this.users=users;
       this. bills=bills;

    }


}

