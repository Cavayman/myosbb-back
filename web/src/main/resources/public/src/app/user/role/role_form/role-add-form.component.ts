import { Component, Output, Input, EventEmitter, OnInit, ViewChild } from '@angular/core';
import {CORE_DIRECTIVES} from '@angular/common';
import { IRole, Role} from '../role';
import {MODAL_DIRECTIVES, BS_VIEW_PROVIDERS} from 'ng2-bootstrap/ng2-bootstrap';
import {ModalDirective} from "ng2-bootstrap/ng2-bootstrap";
@Component({
    selector: 'role-add-form',
    templateUrl: './src/app/user/role/role_form/role-add-form.html',
    directives:[MODAL_DIRECTIVES, CORE_DIRECTIVES],
    viewProviders: [BS_VIEW_PROVIDERS]
})
export class RoleAddFormComponent implements OnInit{
    @Output() created: EventEmitter<Role>;
    @Input() role:IRole;

    @ViewChild('addModal')
    addModal:ModalDirective;

    openAddModal() {
        this.addModal.show();  
    }

    constructor() {
        this.created = new EventEmitter<Role>();
    }

    ngOnInit() {
        if(this.role === undefined){
            this.role = new Role("");
        }
    }

    createRole(name:string) {
        if (name) {
            let role = new Role(name);
            this.created.emit(role);
        }
    }
}