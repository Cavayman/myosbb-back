/**
 * Created by Roma on 10/08/2016.
 */
import {Pipe, PipeTransform} from "@angular/core";
import {IRole} from "./role"

@Pipe({
    name: 'roleFilter'
})
export class RoleFilter implements PipeTransform {

    transform(value: IRole[], args: string): any {

        let result = [];

        for (let val of value) {
            if (val.name) {
                result.push(val);
            }
        }
        return result;
    }
}