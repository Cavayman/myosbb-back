import {Component, OnInit, ViewChild} from "@angular/core";
import {Vote} from './vote';
import {Option} from './option';
import {VoteService} from './vote.service';
import {OptionService} from './option.service';
import {VoteAddFormComponent} from './vote_form/vote-add-form.component';
import {VoteDelFormComponent} from './vote_form/vote-del-form.component';
import {VoteCloseFormComponent}  from './vote_form/vote-close-form.component';
import {User} from './user';
import {CurrentUserService} from "../../../shared/services/current.user.service";
import {TranslatePipe} from "ng2-translate";
import {CapitalizeFirstLetterPipe} from "../../../shared/pipes/capitalize-first-letter";

@Component({
    selector: 'vote',
    templateUrl: './src/app/home/voting/vote.html',
    styleUrls: ['./src/app/home/voting/vote.css'],
    directives: [ VoteAddFormComponent, VoteDelFormComponent, VoteCloseFormComponent],
    providers:[VoteService, OptionService],
    pipes: [CapitalizeFirstLetterPipe, TranslatePipe]
})
export class VoteComponent implements OnInit {

    @ViewChild('voteAddForm')
    voteAddForm:VoteAddFormComponent;

    voteArr: Vote[];
    currentUser: User;

    constructor(private voteService:VoteService, private optionService:OptionService, private currentUserService:CurrentUserService) {
        this.voteArr = [];
    }

    ngOnInit() {
        this.voteService.getAllVotes()
                        .then(voteArr => this.voteArr = voteArr)
                        .then(()=> this.currentUser = this.currentUserService.getUser())
                        .then(()=> this.checkForUserId())
                        .then(()=> this.countNumberOfRespondents())
                        .then(()=> this.calculateProgress());
    }

    checkForUserId(): void {
        for(let i = 0; i < this.voteArr.length; i++) {
            if(this.voteArr[i].available && !(this.voteArr[i].usersId.includes(this.currentUser.userId))){
                this.voteArr[i].available = true;
            } else {
                this.voteArr[i].available = false;
            }
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

    deleteVote(vote: Vote): void {
        this.voteService.deleteVote(vote).then(vote => this.deleteVoteFromArr(vote));
    }

    closeVote(vote: Vote):void {
        console.log("closeVote");
        vote.available = false;
        this.voteService.updateVote(vote);
    }

    private deleteVoteFromArr(vote: Vote): void {
         let index = this.voteArr.indexOf(vote);
         if(index > -1) {
            this.voteArr.splice(index, 1);
         }
    }

    isCurrentUserCreator(vote:Vote): boolean {
        return this.currentUser.userId === vote.user.userId ? true : false;
    }
}   