export interface BillDTO {
    billId: number;
    date: string;
    tariff: number;
    toPay: number;
    paid: number;
    description: string;
    apartmentNumber: number;
    apartmentId:number;
    providerId:number;
    status: string;
}
