/**
 * Created by Anastasiia Fedorak on 8/16/16.
 */

import {Pipe, PipeTransform} from "@angular/core";
import {Contract} from "../models/contract.interface";

@Pipe({
    name: 'active'
})
export class ActiveFilter implements PipeTransform {

    transform(value: Contract[], args: string): any {

        let result = [];

        for (let val of value) {
            if (val.active === true) {
                result.push(val);
            }
        }
        return result;

    }
}