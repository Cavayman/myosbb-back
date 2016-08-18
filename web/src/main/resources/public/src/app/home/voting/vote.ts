import {Option} from './option';
import {User} from './user';

export class Vote {
    voteId: number;
    description: string;
    options: Option[];
    user: User;
    usersId: number[];
    isPrivate: boolean;
    available: boolean;
    numberOfRespondents: number;
    startTime: Date;
    endTime: Date;
}