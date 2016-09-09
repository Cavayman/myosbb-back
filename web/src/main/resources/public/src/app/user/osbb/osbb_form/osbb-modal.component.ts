import { Component, Output, Input, EventEmitter, OnInit, ViewChild } from '@angular/core';
import {FORM_DIRECTIVES, CORE_DIRECTIVES, FormBuilder, Control, ControlGroup, Validators} from '@angular/common';
import {IOsbb, Osbb} from "../../../../shared/models/osbb";
import {MODAL_DIRECTIVES, BS_VIEW_PROVIDERS} from 'ng2-bootstrap/ng2-bootstrap';
import {ModalDirective} from "ng2-bootstrap/ng2-bootstrap";
import {TranslatePipe} from "ng2-translate";
import {CapitalizeFirstLetterPipe} from "../../../../shared/pipes/capitalize-first-letter";

@Component({
    selector: 'osbb-modal',
    templateUrl: './src/app/user/osbb/osbb_form/osbb-modal.html',
    styleUrls: ['./src/app/user/osbb/osbb_form/osbb-modal.css'],
    directives:[MODAL_DIRECTIVES, FORM_DIRECTIVES, CORE_DIRECTIVES],
    viewProviders: [BS_VIEW_PROVIDERS],
    pipes: [TranslatePipe, CapitalizeFirstLetterPipe]
})
export class OsbbModalComponent implements OnInit{
    @Output() created: EventEmitter<Osbb>;
    @Output() update: EventEmitter<Osbb>;

    @ViewChild('modalWindow')
    modalWindow:ModalDirective;

    osbb:IOsbb;
    isEditing:boolean;
    
    logoUrl: string;
    name: string;
    description: string;
    address: string;
    district: string;
    
    submitAttempt:boolean = false;
    creatingForm: ControlGroup;
    logoUrlControl: Control;
    nameControl: Control;
    descriptionControl: Control;
    addressControl: Control;
    districtControl: Control;

     constructor(private builder: FormBuilder) {
        this.created = new EventEmitter<Osbb>();
        this.update = new EventEmitter<Osbb>();
        this.logoUrlControl = new Control('', Validators.required);
        this.nameControl = new Control('', Validators.required);
        this.descriptionControl = new Control('', Validators.required);
        this.addressControl = new Control('', Validators.required);
        this.districtControl = new Control('', Validators.required);
        this.creatingForm = builder.group({
            nameControl: this.nameControl,
            descriptionControl: this.descriptionControl,
            addressControl: this.addressControl,
            districtControl: this.districtControl,
            logoUrlControl: this.logoUrlControl
        });
    }

     ngOnInit() {
        if(this.osbb === undefined){
            this.osbb = new Osbb("","","","","");
        }
    }

    openAddModal() {
        this.isEditing = false;
        this.modalWindow.show();  
    }

     openEditModal(osbb:IOsbb) {
        this.isEditing = true;
        this.osbb = osbb;
        this.logoUrl = osbb.logoUrl;
        this.name = osbb.name;
        this.description = osbb.description;
        this.address = osbb.address;
        this.district = osbb.district;
        this.modalWindow.show();
    }

    saveButtonAction():void {
         this.submitAttempt = true;
         if(this.logoUrlControl.valid && this.nameControl.valid && this.descriptionControl.valid 
                                && this.addressControl.valid && this.districtControl.valid) {
            this.modalWindow.hide();
            if(this.isEditing) {
                this.editOsbb();
                this.update.emit(this.osbb);
            } else {
                this.created.emit(this.createOsbb());
            }
            this.clearAddForm();
        }
    }

    createOsbb():IOsbb {
        let osbb = new Osbb();
        osbb.name = this.name;
        osbb.description = this.description;
        osbb.address = this.address;
        osbb.district = this.district;
        osbb.logoUrl = this.logoUrl;
        osbb.creationDate = new Date();   
        return osbb;
    }

    editOsbb() {
           this.osbb.logoUrl = this.logoUrl;
           this.osbb.name = this.name;
           this.osbb.description = this.description;
           this.osbb.address = this.address;
           this.osbb.district = this.district;
    }

     clearAddForm() {
        this.logoUrl='';
        this.name='';
        this.description='';
        this.address='';
        this.district='';
        this.submitAttempt = false;
    }
}