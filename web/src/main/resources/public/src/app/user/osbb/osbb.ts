export interface IOsbb {
    osbbId: number;
    name: string;
    description: string;
}

export class Osbb implements IOsbb {
    osbbId: number;
    name: string;
    description: string;

    constructor( name:string, description:string) {
        this.name = name;
        this.description = description;
    }
}