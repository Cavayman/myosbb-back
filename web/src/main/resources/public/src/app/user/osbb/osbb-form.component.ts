<<<<<<< e4b7a9415d6cfc7cd95ab13cff81e0ee4f1d29b2
import {Component, Output, Input, EventEmitter, OnInit, ViewChild} from "@angular/core";
import {MODAL_DIRECTIVES, ModalComponent} from "ng2-bs3-modal/ng2-bs3-modal";
import {IOsbb, Osbb} from "./osbb";
=======
import { Component, Output, Input, EventEmitter, OnInit, ViewChild } from '@angular/core';
import { MODAL_DIRECTIVES, ModalComponent } from 'ng2-bs3-modal/ng2-bs3-modal';
import { IOsbb, Osbb} from './osbb';
>>>>>>> fixing errors

@Component({
    selector: 'osbb-form',
    templateUrl: './src/app/user/osbb/osbb-form.component.html',
<<<<<<< e4b7a9415d6cfc7cd95ab13cff81e0ee4f1d29b2
    directives:[MODAL_DIRECTIVES]
})
export class OsbbFormComponent implements OnInit{
    @Output() created: EventEmitter<Osbb>;
    @Output() updated: EventEmitter<Osbb>;
=======
    directives: [MODAL_DIRECTIVES]
})
export class OsbbFormComponent implements OnInit {
    @Output() created:EventEmitter<Osbb>;
    @Output() updated:EventEmitter<Osbb>;
>>>>>>> fixing errors
    @Input() osbb:IOsbb;

    @ViewChild('updateModal')
    updateModal:ModalComponent;

    openUpdateModal() {
<<<<<<< e4b7a9415d6cfc7cd95ab13cff81e0ee4f1d29b2
        this.updateModal.open();  
=======
        this.updateModal.open();
>>>>>>> fixing errors
    }

    closeUpdateModal() {
        this.updateModal.close();
    }

    constructor() {
        this.created = new EventEmitter<Osbb>();
        this.updated = new EventEmitter<Osbb>();
    }

    ngOnInit() {
<<<<<<< e4b7a9415d6cfc7cd95ab13cff81e0ee4f1d29b2
        if(this.osbb === undefined){
=======
        if (this.osbb === undefined) {
>>>>>>> fixing errors
            this.osbb = new Osbb("", "");
        }
    }

    createOsbb(name:string, description:string) {
        if (name) {
            let osbb = new Osbb(name, description);
            this.created.emit(osbb);
        }
    }

    editOsbb(name:string, description:string) {
        if (name) {
<<<<<<< e4b7a9415d6cfc7cd95ab13cff81e0ee4f1d29b2
           this.osbb.name = name;
           this.osbb.description = description;
           this.updated.emit(this.osbb);
=======
            this.osbb.name = name;
            this.osbb.description = description;
            this.updated.emit(this.osbb);
>>>>>>> fixing errors
        }
    }
}