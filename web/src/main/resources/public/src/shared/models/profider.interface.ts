import {ProviderType} from "./provider.type.interface";
export interface Provider {
    providerId:number;
    name:string,
    description:string,
    logoUrl:string,
    periodicity:string
    type:number
}