import {Component, OnInit} from '@angular/core';
import {HTTP_PROVIDERS} from "@angular/http";
import { MODAL_DIRECTIVES} from 'ng2-bs3-modal/ng2-bs3-modal';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/toPromise';

import {IRole} from './role';
import { RoleService } from './role.service';
import { RoleFormComponent } from './role-form.component';

@Component({
    selector: 'role',
    templateUrl: './src/app/user/role/role.component.html',
    directives:[RoleFormComponent, MODAL_DIRECTIVES],
    providers: [HTTP_PROVIDERS, RoleService]
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

    onRoleCreated(role:IRole): void {
        this.roleService.addRole(role).then(role => this.addRole(role));
    }

    onRoleEdited(role:IRole): void {
        this.roleService.editRole(role);
    }

    onRoleDeleted(role:IRole): void {
        if(confirm("Are you sure?")) {
            this.roleService.deleteRole(role).then(role => this.deleteRole(role));
        }
    }

    private addRole(role: IRole): void {
        this.roleArr.push(role);
    }

     private deleteRole(role: IRole): void {
         let index = this.roleArr.indexOf(role);
         if(index > -1) {
            this.roleArr.splice(index, 1);
         }
    }
}