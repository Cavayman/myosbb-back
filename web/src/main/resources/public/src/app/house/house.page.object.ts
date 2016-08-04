export class HousePageObject {
    houseId: number;
    city: string = '';
    street: string = '';
    zipCode: string = '';
    head: {
        firstName: string,
        lastName: string,
        email: string,
        phoneNumber: string

    } = {
        firstName: '',
        lastName: '',
        email: '',
        phoneNumber: ''
    };
}