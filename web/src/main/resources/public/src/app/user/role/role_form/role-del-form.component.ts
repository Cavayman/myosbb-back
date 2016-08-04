import { Component, Output, Input, EventEmitter, OnInit, ViewChild } from '@angular/core';
import {CORE_DIRECTIVES} from '@angular/common';
import { IRole, Role} from '../role';
import {MODAL_DIRECTIVES, BS_VIEW_PROVIDERS} from 'ng2-bootstrap/ng2-bootstrap';
import {ModalDirective} from "ng2-bootstrap/ng2-bootstrap";
@Component({
    selector: 'role-del-form',
    templateUrl: './src/app/user/role/role_form/role-del-form.html',
    directives:[MODAL_DIRECTIVES, CORE_DIRECTIVES],
    viewProviders: [BS_VIEW_PROVIDERS]
})
export class RoleDelFormComponent {
    @Output() delete: EventEmitter<IRole>;
    private role: IRole;
    @ViewChild('delModal')
    delModal:ModalDirective;

    openDelModal(roleId:IRole): void {
        this.role = roleId;
        this.delModal.show();   
    }

    constructor() {
        this.delete = new EventEmitter<IRole>();
    }

   delRole(): void {
        this.delete.emit(this.role);
    }
}