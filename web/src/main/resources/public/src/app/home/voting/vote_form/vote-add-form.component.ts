import { Component, Output, Input, EventEmitter, ViewChild } from '@angular/core';
import {FORM_DIRECTIVES, CORE_DIRECTIVES, FormBuilder, Control, ControlGroup, Validators} from '@angular/common';
import {MODAL_DIRECTIVES, BS_VIEW_PROVIDERS} from 'ng2-bootstrap/ng2-bootstrap';
import {ModalDirective} from "ng2-bootstrap/ng2-bootstrap";
import {Vote} from '../vote';
import {User} from '../user';
import {Option} from '../option';

@Component({
    selector: 'vote-add-form',
    templateUrl: './src/app/home/voting/vote_form/vote-add-form.html',
    styleUrls:['./src/app/home/voting/vote_form/vote-add-form.css'],
    directives:[FORM_DIRECTIVES, MODAL_DIRECTIVES, CORE_DIRECTIVES],
    viewProviders: [BS_VIEW_PROVIDERS]
})
export class VoteAddFormComponent {

    optionArr:Option[];
    @Output() create: EventEmitter<Vote>;

    @ViewChild('addVoteModal')
    addVoteModal:ModalDirective;

    @Input() currentUser:User;

    creatingForm: ControlGroup;
    question: Control;
    submitAttempt: boolean;
    optionInputStr: string;
    questionInputStr: string;
    endTimeStr: string;

    openAddModal() {
        this.addVoteModal.show();  
    }

    closeAddModal() {
        this.clearAddModal();
        this.addVoteModal.hide(); 
    }

    clearAddModal() {
        this.submitAttempt = false;
        this.optionArr = [];
        this.endTimeStr = '';
        this.questionInputStr = '';
        this.optionInputStr='';
    }

    constructor(private builder: FormBuilder) {
        this.create = new EventEmitter<Vote>();
        this.optionArr = [];
        this.submitAttempt = false;
        this.question = new Control('', Validators.required);
        this.creatingForm = builder.group({
            question: this.question,
        });
        this.optionInputStr="";
    }

    addOption(description:string):void {
        let opt = new Option();
        opt.description = description;
        opt.users = [];
        this.optionArr.push(opt);
        this.optionInputStr="";
    }

    toggleSubmitAttempt(){
        this.submitAttempt = true;
    }

    onCreateVoting():void {
        if(this.question.valid && this.isOptionArrSizeCorrect() && this.isEndTimeCorrect()) {
            this.create.emit(this.createVote());
            this.closeAddModal();
        }
    }

    createVote():Vote {
        let vote = new Vote();
        vote.description = this.questionInputStr;
        vote.options = this.optionArr;
        vote.available = true;
        vote.usersId = [];
        vote.startTime = new Date();
        vote.endTime =  this.castEndTimeStringToDate();
        vote.user = this.currentUser;
        return vote;
    }

    castEndTimeStringToDate(): Date {
        let endTime = new Date(this.endTimeStr);
        endTime.setHours(endTime.getHours()-3);
        return endTime;
    }

    isEndTimeCorrect():boolean {
        let startTime = new Date();
        let res = this.castEndTimeStringToDate().valueOf() - startTime.valueOf();
        return res > 0 || res===NaN ? true: false;
    }

    isOptionInputEmpty():boolean {
        return this.optionInputStr.length == 0 ? true: false;
    }

    isOptionArrSizeCorrect(): boolean {
        return this.optionArr.length >= 2? true: false;
    }

    deleteOption(option: Option) {
        this.optionArr.splice(this.optionArr.indexOf(option),1);
    }
}