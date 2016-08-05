import { Component, Output, Input, EventEmitter, OnInit, ViewChild } from '@angular/core';
import {CORE_DIRECTIVES} from '@angular/common';
import {MODAL_DIRECTIVES, BS_VIEW_PROVIDERS} from 'ng2-bootstrap/ng2-bootstrap';
import {ModalDirective} from "ng2-bootstrap/ng2-bootstrap";
import {Vote} from '../vote';

@Component({
    selector: 'vote-add-form',
    templateUrl: './src/app/home/voting/vote_form/vote-add-form.html',
    styleUrls:['./src/app/home/voting/vote_form/vote-add-form.css'],
    directives:[MODAL_DIRECTIVES, CORE_DIRECTIVES],
    viewProviders: [BS_VIEW_PROVIDERS]
})
export class VoteAddFormComponent implements OnInit{
    @Output() create: EventEmitter<Vote>;

    @ViewChild('addVoteModal')
    addVoteModal:ModalDirective;
    optionArr:string[];
    openAddModal() {
        this.addVoteModal.show();  
    }

    constructor() {
        this.create = new EventEmitter<Vote>();
        this.optionArr = [];
    }
    addOption(option:string):void {
        if(this.optionArr.length <= 9){
            this.optionArr.push(option);
        } else{
            console.log("arr is full.Can't add enymore.");
        }
            
    }

    ngOnInit() {
       
    }

    toggleCheckBox() {
        
    }

    onCreateVoting(question: string, isPrivate: boolean):void {
        console.log("isPrivate: " + isPrivate);
        let vote = new Vote();
        vote.isPrivate = isPrivate;
        vote.question = question;
        vote.options = this.optionArr;
        this.optionArr = [];
        console.log("arrlength" + this.optionArr.length);

       // console.log("Object:  name="  + vote.question +"    opp: " +vote.options.toString());
        this.create.emit(vote);
    }
}