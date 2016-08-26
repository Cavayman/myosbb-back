import {Component, OnInit, ViewChild} from "@angular/core";
import {VoteAddFormComponent} from './vote_form/vote-add-form.component';
import {Vote} from './vote';
import {Option} from './option';
import {VoteService} from './vote.service';
import {OptionService} from './option.service';
import {User} from './user';
import {CurrentUserService} from "../../../shared/services/current.user.service";

@Component({
    selector: 'vote',
    templateUrl: './src/app/home/voting/vote.html',
    styleUrls: ['./src/app/home/voting/vote.css'],
    directives: [ VoteAddFormComponent],
    providers:[VoteService, OptionService]
})
export class VoteComponent implements OnInit {

    @ViewChild('voteAddForm')
    voteAddForm:VoteAddFormComponent;
    voteArr: Vote[];
    currentUser: User;

    constructor(private voteService:VoteService, private optionService:OptionService, private currentUserService:CurrentUserService) {
        this.voteArr = [];
        this.currentUser = currentUserService.getUser();
        if(this.currentUser.userId===undefined) {                   //    XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX fix this before demo
            console.log("userId is undefined. userId = 1");
            this.currentUser.userId = 1;
        }
    }

    ngOnInit() {
        this.voteService.getAllVotes()
                        .then(voteArr => this.voteArr = voteArr)
                        .then(()=> this.checkForUserId())
                        .then(()=> this.countNumberOfRespondents())
                        .then(() => this.calculateProgress());
    }

    checkForUserId(): void {
        for(let i = 0; i < this.voteArr.length; i++) {
            if(this.voteArr[i].available && !(this.voteArr[i].usersId.includes(this.currentUser.userId))){
                this.voteArr[i].available = true;
            } else {
                this.voteArr[i].available = false;
            }
            //this.voteArr[i].available = this.voteArr[i].usersId.includes(this.currentUser.userId);
        }
    }

    countNumberOfRespondents():void { 
        for(let i = 0; i < this.voteArr.length;i++) {
            this.voteArr[i].numberOfRespondents = this.voteArr[i].usersId.length;
        } 
    }

    openModalWindow(): void {
        console.log("currentUserId: " + this.currentUserService.getUser().userId);
        this.voteAddForm.openAddModal();
    }

    toScoreOption(option: Option, vote: Vote):void {
        if((vote.endTime.valueOf() - new Date().valueOf()) > 0){
            if(vote.numberOfRespondents === undefined) vote.numberOfRespondents = 0 ;
            vote.numberOfRespondents++;
            option.users.push(this.currentUser);
            this.optionService.toScoreOption(option.optionId, this.currentUser.userId);
        }
        vote.available = false;
        this.calcProgressForVote(vote);
    }

    createVote(vote: Vote): void {
        this.voteService.addVote(vote).then(vote => this.addVote(vote));
    }

    private addVote(vote: Vote): void {
        console.log("into addVote: " + vote.user);
        this.voteArr.unshift(vote);
    }
    
    calculateProgress() {
        for(let i = 0; i < this.voteArr.length; i++) {
            this.calcProgressForVote(this.voteArr[i]);
        }
    }

    private calcProgressForVote(vote:Vote): void {
        for(let i = 0 ; i < vote.options.length; i++) {
            vote.options[i].progress = this.calcProgressForOption(vote.options[i].users.length, vote.numberOfRespondents);
        }
    }

     private calcProgressForOption(usersLength: number, numberOfRespondents: number): string{
        if(numberOfRespondents !== 0 ) {
            return String(Math.round(usersLength / numberOfRespondents  * 100));
        } else {
            return "0";
        }
    }

    getStartTime(startTime:Date):string {
        return new Date(startTime).toLocaleString();
    }

     getEndTime(endTime: Date):string {
        return new Date(endTime).toLocaleString();
    }
}   