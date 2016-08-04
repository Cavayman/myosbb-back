import {Pipe, PipeTransform} from "@angular/core";
import {Report} from "./report.interface";

@Pipe({
    name: 'reportFilter'
})
export class ReportFilter implements PipeTransform {


    transform(value: Report[], args: string): any {

        let result = [];

        for (let val of value) {
            if (val.creationDate.match('^.*' + args + '.*?')) {
                result.push(val);
            }
        }
        return result;
    }
}