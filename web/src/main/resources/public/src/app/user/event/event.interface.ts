import {DateTime} from "ng2-datetime-picker/dist/datetime";
export class Event {
    id:number;
    title:string;
    author: string;
    description:string;
    start:DateTime;
    end:DateTime;
    repeat:string;
    path:string;
    constructor(eventItem?:{id:number, title:string, author:string, description:string, start:string,
                            end:string, repeat:string, path:string}) {
    if(eventItem) {
        Object.assign(this,eventItem);
        }
    }
}