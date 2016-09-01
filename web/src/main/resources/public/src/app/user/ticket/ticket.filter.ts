import {Pipe, PipeTransform} from "@angular/core";
import {User} from "./../user";

@Pipe({
    name: 'ticketFilter',
})
export class TicketFilter implements PipeTransform {


    transform(value:User[], args:string):any {

        let result = [];

        for (let val of value) {
            if (val.firstName.match('^.*' + args + '.*?')) {
                result.push(val);
            }

        }
        return result;
    }
}