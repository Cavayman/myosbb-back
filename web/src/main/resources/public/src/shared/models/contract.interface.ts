/**
 * Created by Anastasiia Fedorak on 8/5/16.
 */

export interface Contract {
    contractId: number;
    dateStart: string;
    dateFinish: string;
    text: String;
    price: number;
    attachment: string;
    osbb: string;
    provider: string;
}