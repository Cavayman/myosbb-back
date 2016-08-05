/**
 * Created by Anastasiia Fedorak on 8/5/16.
 */

export interface Contract {
    contractId: number;
    provider: string;
    dateStart: string;
    dateFinish: string;
    text: String;
    price: number;
    attachment: string;
}