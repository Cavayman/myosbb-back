import { Component, Output, Input, EventEmitter, OnInit, ViewChild } from '@angular/core';
import {CORE_DIRECTIVES} from '@angular/common';
import { IRole, Role} from '../role';
import {MODAL_DIRECTIVES, BS_VIEW_PROVIDERS} from 'ng2-bootstrap/ng2-bootstrap';
import {ModalDirective} from "ng2-bootstrap/ng2-bootstrap";
@Component({
    selector: 'role-edit-form',
    templateUrl: './src/app/user/role/role_form/role-edit-form.html',
    directives:[MODAL_DIRECTIVES, CORE_DIRECTIVES],
    viewProviders: [BS_VIEW_PROVIDERS]
})
export class RoleEditFormComponent implements OnInit{
    @Output() update: EventEmitter<Role>;
    @Input() role:IRole;

    @ViewChild('editModal')
    editModal:ModalDirective;

    openEditModal() {
        this.editModal.show();  
    }

    constructor() {
        this.update = new EventEmitter<Role>();
    }

    ngOnInit() {
        if(this.role === undefined){
            this.role = new Role("");
        }
    }

    editRole(name:string) {
        if (name) {
           this.role.name = name;
           this.update.emit(this.role);
        }
    }
}