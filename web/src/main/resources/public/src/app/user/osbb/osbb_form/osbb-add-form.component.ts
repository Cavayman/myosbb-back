import { Component, Output, Input, EventEmitter, OnInit, ViewChild } from '@angular/core';
import {CORE_DIRECTIVES} from '@angular/common';
import {IOsbb, Osbb} from "../../../../shared/models/osbb";
import {MODAL_DIRECTIVES, BS_VIEW_PROVIDERS} from 'ng2-bootstrap/ng2-bootstrap';
import {ModalDirective} from "ng2-bootstrap/ng2-bootstrap";
@Component({
    selector: 'osbb-add-form',
    templateUrl: './src/app/user/osbb/osbb_form/osbb-add-form.html',
    styleUrls: ['./src/app/user/osbb/osbb_form/osbb-add-form.css'],
    directives:[MODAL_DIRECTIVES, CORE_DIRECTIVES],
    viewProviders: [BS_VIEW_PROVIDERS]
})
export class OsbbAddFormComponent implements OnInit{
    @Output() created: EventEmitter<Osbb>;
    @Input() osbb:IOsbb;

    @ViewChild('addModal')
    addModal:ModalDirective;

    name: string;
    description: string;
    address: string;
    district: string;
    logoUrl: string;

    openAddModal() {
        this.addModal.show();  
    }

    constructor() {
        this.created = new EventEmitter<Osbb>();
    }

    ngOnInit() {
        if(this.osbb === undefined){
            this.osbb = new Osbb("","","","","");
        }
    }

    createOsbb() {
        let osbb = new Osbb(this.name, this.description, this.address, this.district, this.logoUrl);
        osbb.creationDate = new Date();
        this.created.emit(osbb);
    }
}