import { Component, Output, Input, EventEmitter, ViewChild } from '@angular/core';
import {CORE_DIRECTIVES} from '@angular/common';
import {MODAL_DIRECTIVES, BS_VIEW_PROVIDERS} from 'ng2-bootstrap/ng2-bootstrap';
import {ModalDirective} from "ng2-bootstrap/ng2-bootstrap";
import {Vote} from '../vote';
import {User} from '../user';
import {Option} from '../option';

@Component({
    selector: 'vote-add-form',
    templateUrl: './src/app/home/voting/vote_form/vote-add-form.html',
    styleUrls:['./src/app/home/voting/vote_form/vote-add-form.css'],
    directives:[MODAL_DIRECTIVES, CORE_DIRECTIVES],
    viewProviders: [BS_VIEW_PROVIDERS]
})
export class VoteAddFormComponent {

    optionArr:Option[];
    @Output() create: EventEmitter<Vote>;

    @ViewChild('addVoteModal')
    addVoteModal:ModalDirective;

    @Input() currentUser:User;


    openAddModal() {
        this.addVoteModal.show();  
    }

    constructor() {
        this.create = new EventEmitter<Vote>();
        this.optionArr = [];
    }

    addOption(description:string):void {
        let opt = new Option();
        opt.description = description;
        opt.users = [];
        this.optionArr.push(opt); 
    }

    onCreateVoting(question: string, time:any):void {
        console.log("time string: " + time);
        let vote = new Vote();
        vote.description = question;
        vote.options = this.optionArr;
        vote.available = true;
        this.optionArr = [];
        vote.usersId = [];
        vote.startTime = new Date();
        let endTime = new Date(time);
        endTime.setHours(endTime.getHours()-3);
        vote.endTime = endTime;
        vote.user = this.currentUser;
        //this.printTime(vote);
        this.create.emit(vote);
    }

    printTime(vote: Vote) {
        console.log("start time: " + vote.startTime);
        console.log("end time: " + vote.endTime);
    }
}