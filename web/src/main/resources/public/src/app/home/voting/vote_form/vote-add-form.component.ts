import { Component, Output, EventEmitter, ViewChild } from '@angular/core';
import {CORE_DIRECTIVES} from '@angular/common';
import {MODAL_DIRECTIVES, BS_VIEW_PROVIDERS} from 'ng2-bootstrap/ng2-bootstrap';
import {ModalDirective} from "ng2-bootstrap/ng2-bootstrap";
import {Vote} from '../vote';
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

    openAddModal() {
        this.addVoteModal.show();  
    }

    constructor() {
        this.create = new EventEmitter<Vote>();
        this.optionArr = [];
    }

    addOption(option:string):void {
        if(this.optionArr.length <= 9){
            let opt = new Option();
            opt.description = option;
            opt.users = [];
            this.optionArr.push(opt);
        } else{
            console.log("arr is full.Can't add enymore.");
        }     
    }

    onCreateVoting(question: string, isPrivate: boolean):void {
        let vote = new Vote();
        vote.isPrivate = isPrivate;
        vote.description = question;
        vote.options = this.optionArr;
        vote.usersId = [];
        this.optionArr = [];
        this.create.emit(vote);
    }
}