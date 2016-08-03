export interface IRole {
    roleId: number;
    name: string;
}

export class Role implements IRole {
    roleId: number;
    name: string;

    constructor( name:string) {
        this.name = name;
    }
}