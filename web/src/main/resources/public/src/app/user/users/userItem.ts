import assign = require("core-js/library/fn/object/assign");
export class userItem {
    userId:number;
    firstName:string;
    lastName:string;
    birthDate:string;
    email:string;
    phoneNumber:string;
    gender:string;
    constructor(userItem?:{userId:number,firstName:string,lastName:string,birthDate:string,email:string,phoneNumber:string,gender:string}) {
        if(userItem) {
            Object.assign(this,userItem);
        }
    }
}