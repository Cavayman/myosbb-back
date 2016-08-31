import { Component, Output, Input, EventEmitter, OnInit, ViewChild } from '@angular/core';
import {CORE_DIRECTIVES} from '@angular/common';
import {Vote} from "../vote";
import {MODAL_DIRECTIVES, BS_VIEW_PROVIDERS} from 'ng2-bootstrap/ng2-bootstrap';
import {ModalDirective} from "ng2-bootstrap/ng2-bootstrap";
import {TranslatePipe} from "ng2-translate";
import {CapitalizeFirstLetterPipe} from "../../../../shared/pipes/capitalize-first-letter";

@Component({
    selector: 'vote-close-form',
    templateUrl: './src/app/home/voting/vote_form/vote-close-form.html',
    directives:[MODAL_DIRECTIVES, CORE_DIRECTIVES],
    viewProviders: [BS_VIEW_PROVIDERS],
    pipes: [TranslatePipe, CapitalizeFirstLetterPipe]
})
export class VoteCloseFormComponent {

    @Output() close: EventEmitter<Vote>;
    private vote: Vote;

    @ViewChild('closeModal')
    closeModal:ModalDirective;

    openCloseModal(vote:Vote): void {
        this.vote = vote;
        this.closeModal.show();   
    }
    constructor() {
        this.close = new EventEmitter<Vote>();
    }

    getEndTime():string {
        let time;
        if(this.vote !== undefined ) {
            time = this.vote.endTime;
        }
        return new Date(time).toLocaleString();
    }

   closeVote(): void {
        this.close.emit(this.vote);
    }
}