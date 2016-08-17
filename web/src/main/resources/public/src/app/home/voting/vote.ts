import {Option} from './option';

export class Vote {
    voteId: number;
    description: string;
    isPrivate: boolean;
    available: boolean;
    options: Option[];
    usersId: number[];
    numberOfRespondents: number;
}