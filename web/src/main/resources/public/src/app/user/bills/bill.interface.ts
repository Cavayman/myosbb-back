export interface Bill {
    billId: number;
    date: string;
    tariff: number;
    toPay: number;
    paid: number;
    description: string;
    apartmentNumber: number;
    status: string;
}
