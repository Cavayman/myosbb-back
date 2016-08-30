import { Component, Output, Input, EventEmitter, OnInit, ViewChild } from '@angular/core';
import {CORE_DIRECTIVES} from '@angular/common';
import {Vote} from "../vote";
import {MODAL_DIRECTIVES, BS_VIEW_PROVIDERS} from 'ng2-bootstrap/ng2-bootstrap';
import {ModalDirective} from "ng2-bootstrap/ng2-bootstrap";
import {TranslatePipe} from "ng2-translate";
import {CapitalizeFirstLetterPipe} from "../../../../shared/pipes/capitalize-first-letter";

@Component({
    selector: 'vote-del-form',
    templateUrl: './src/app/home/voting/vote_form/vote-del-form.html',
    directives:[MODAL_DIRECTIVES, CORE_DIRECTIVES],
    viewProviders: [BS_VIEW_PROVIDERS],
    pipes: [TranslatePipe, CapitalizeFirstLetterPipe]
})
export class VoteDelFormComponent {

    @Output() delete: EventEmitter<Vote>;
    private vote: Vote;
    @ViewChild('delModal')

    delModal:ModalDirective;

    openDelModal(vote:Vote): void {
        this.vote = vote;
        this.delModal.show();   
    }

    constructor() {
        this.delete = new EventEmitter<Vote>();
    }

   delVote(): void {
        this.delete.emit(this.vote);
    }
}