import {ProviderType} from "./provider.type.interface";
export interface Provider {
    providerId:number;
    name:string,
    description:string,
    logoUrl:string,
    periodicity:string,
    type: ProviderType,
    email:string,
    phone:string,
    address:string,
    schedule: string,
    active: boolean
}