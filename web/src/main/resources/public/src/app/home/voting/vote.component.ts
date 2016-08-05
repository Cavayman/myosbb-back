import {Component, OnInit, Output, EventEmitter, ViewChild} from "@angular/core";
import {VoteAddFormComponent} from './vote_form/vote-add-form.component';
import {Vote} from './vote';

@Component({
    selector: 'vote',
    templateUrl: './src/app/home/voting/vote.html',
    styleUrls: ['./src/app/home/voting/vote.css'],
    directives: [ VoteAddFormComponent]
})
export class VoteComponent implements OnInit {

    @Output() create: EventEmitter<Vote>;
    @ViewChild('voteAddForm')
    voteAddForm:VoteAddFormComponent;

    constructor() {
        this.create = new EventEmitter<Vote>();
    }
    ngOnInit():any {
    }

    openModalWindow() {
        this.voteAddForm.openAddModal();
    }

    onCreateVoting(vote:Vote): void  {
        console.log("Додаю голосування....");
        this.create.emit(vote);
    }
}