import { Component, Output, Input, EventEmitter, OnInit, ViewChild } from '@angular/core';
import {CORE_DIRECTIVES} from '@angular/common';
import {IOsbb, Osbb} from "../../../../shared/models/osbb";
import {MODAL_DIRECTIVES, BS_VIEW_PROVIDERS} from 'ng2-bootstrap/ng2-bootstrap';
import {ModalDirective} from "ng2-bootstrap/ng2-bootstrap";
@Component({
    selector: 'osbb-edit-form',
    templateUrl: './src/app/user/osbb/osbb_form/osbb-edit-form.html',
    directives:[MODAL_DIRECTIVES, CORE_DIRECTIVES],
    viewProviders: [BS_VIEW_PROVIDERS]
})
export class OsbbEditFormComponent implements OnInit{
    @Output() update: EventEmitter<Osbb>;
    @Input() osbb:IOsbb;

    @ViewChild('editModal')
    editModal:ModalDirective;

    openEditModal() {
        this.editModal.show();  
    }

    constructor() {
        this.update = new EventEmitter<Osbb>();
    }

    ngOnInit() {
        if(this.osbb === undefined){
            this.osbb = new Osbb("", "","","","");
        }
    }

    editOsbb(name:string, description:string, address: string, district: string, logoUrl:string) {
           this.osbb.name = name;
           this.osbb.description = description;
           this.osbb.address = address;
           this.osbb.district = district;
           this.osbb.logoUrl = logoUrl;
           this.update.emit(this.osbb);
    }
}