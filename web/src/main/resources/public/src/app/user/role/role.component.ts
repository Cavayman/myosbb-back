import {Component, OnInit} from '@angular/core';
import {CORE_DIRECTIVES} from '@angular/common';
import {HTTP_PROVIDERS} from "@angular/http";
import {MODAL_DIRECTIVES, BS_VIEW_PROVIDERS} from 'ng2-bootstrap/ng2-bootstrap';
import "rxjs/add/operator/map";
import "rxjs/add/operator/toPromise";
import {IRole} from './role';
import { RoleService } from './role.service';
import { RoleAddFormComponent } from './role_form/role-add-form.component';
import { RoleEditFormComponent } from './role_form/role-edit-form.component';
import { RoleDelFormComponent } from './role_form/role-del-form.component';

@Component({
    selector: 'role',
    templateUrl: './src/app/user/role/role.component.html',
    providers: [HTTP_PROVIDERS, RoleService],
    directives: [MODAL_DIRECTIVES, CORE_DIRECTIVES, RoleAddFormComponent, RoleEditFormComponent, RoleDelFormComponent],
    viewProviders: [BS_VIEW_PROVIDERS]
})
export class RoleComponent implements OnInit {
    
    roleArr:IRole[];
    updatedRole:IRole;

    constructor( private roleService: RoleService) {
        this.roleArr = [];
    }
    ngOnInit() {
        this.roleService.getAllRole().then(roleArr => this.roleArr = roleArr);
    }

    private initUpdatedRole(role:IRole): void {
        this.updatedRole = role;
    }

    createRole(role:IRole): void {
        this.roleService.addRole(role).then(role => this.addRole(role));
    }

    editRole(role:IRole): void {
        this.roleService.editRole(role);
    }

    deleteRole(role:IRole): void {
        this.roleService.deleteRole(role).then(role => this.deleteRoleFromArr(role));
    }

    private addRole(role: IRole): void {
        this.roleArr.push(role);
    }

    private deleteRoleFromArr(role: IRole): void {
        let index = this.roleArr.indexOf(role);
        if(index > -1) {
            this.roleArr.splice(index, 1);
        }
    }
}