export interface Ticket {
    ticketId: number;
    name: string;
    description: string;
    //state: TicketState;
    //message:Message[];
    time:string;
}
export enum TicketState{
    NEW,
    IN_PROGRESS,
    DONE
}