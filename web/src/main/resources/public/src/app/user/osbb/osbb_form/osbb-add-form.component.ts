import { Component, Output, Input, EventEmitter, OnInit, ViewChild } from '@angular/core';
import {CORE_DIRECTIVES} from '@angular/common';
import { IOsbb, Osbb} from '../osbb';
import {MODAL_DIRECTIVES, BS_VIEW_PROVIDERS} from 'ng2-bootstrap/ng2-bootstrap';
import {ModalDirective} from "ng2-bootstrap/ng2-bootstrap";
@Component({
    selector: 'osbb-add-form',
    templateUrl: './src/app/user/osbb/osbb_form/osbb-add-form.html',
    directives:[MODAL_DIRECTIVES, CORE_DIRECTIVES],
    viewProviders: [BS_VIEW_PROVIDERS]
})
export class OsbbAddFormComponent implements OnInit{
    @Output() created: EventEmitter<Osbb>;
    @Input() osbb:IOsbb;

    @ViewChild('addModal')
    addModal:ModalDirective;

    openAddModal() {
        this.addModal.show();  
    }

    constructor() {
        this.created = new EventEmitter<Osbb>();
    }

    ngOnInit() {
        if(this.osbb === undefined){
            this.osbb = new Osbb("", "");
        }
    }

    createOsbb(name:string, description:string) {
        if (name) {
            let osbb = new Osbb(name, description);
            this.created.emit(osbb);
        }
    }
}