import assign = require("core-js/library/fn/object/assign");
export class User {
    userId:number;
    firstName:string;
    lastName:string;
    birthDate:string;
    email:string;
    phoneNumber:string;
    gender:string;
    password:number;
    activated:boolean;
    apartment:any;
    role:string;
     
      constructor() {
     this.userId=new Number();
    this.firstName=new String();
    this.lastName=new String();
    this.birthDate=new String();
    this.email=new String();
    this.phoneNumber=new String();
    this.gender=new String();
    this.password=new Number();
    this.activated=new Boolean();
    this.role=new String();
  }
    constructor(userItem?:{userId:number,firstName:string,lastName:string,birthDate:string,email:string,phoneNumber:string,gender:string,password:number,activated:boolean,role:string,apartment:any}) {
        if(userItem) {
            Object.assign(this,userItem);
        }
    }
}