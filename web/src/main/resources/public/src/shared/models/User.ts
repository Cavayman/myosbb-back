import assign = require("core-js/library/fn/object/assign");
export class User {
    userId:number;
    firstName:string;
    lastName:string;
    birthDate:string;
    email:string;
    phoneNumber:string;
    gender:string;
    password:number
    constructor(userItem?:{userId:number,firstName:string,lastName:string,birthDate:string,email:string,phoneNumber:string,gender:string,password:number}) {
        if(userItem) {
            Object.assign(this,userItem);
        }
    }
}