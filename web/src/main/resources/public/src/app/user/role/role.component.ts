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
import {RoleFilter} from "./role.filter";
import {PageCreator} from "../../../shared/services/page.creator.interface";

@Component({
    selector: 'role',
    templateUrl: './src/app/user/role/role.component.html',
    providers: [HTTP_PROVIDERS, RoleService],
    directives: [MODAL_DIRECTIVES, CORE_DIRECTIVES, RoleAddFormComponent, RoleEditFormComponent, RoleDelFormComponent],
    viewProviders: [BS_VIEW_PROVIDERS],
    pipes: [RoleFilter]
})
export class RoleComponent implements OnInit {

    roleArr:IRole[];
    updatedRole:IRole;
    private pageCreator: PageCreator<IRole>;
    private pageNumber: number = 1;
    private pageList: Array<number> = [];
    private totalPages: number;
    order: boolean = true;

    constructor( private roleService: RoleService) {
        this.roleArr = [];
    }
    ngOnInit(): any {
        this.getRolesByPageNum(this.pageNumber);
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
        console.log('roleTs');
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

    getRolesByPageNum(pageNumber: number) {
        this.pageNumber = +pageNumber;
        this.emptyArray();
        return this.roleService.getAllRole(this.pageNumber)
            .subscribe((data) => {
                    this.pageCreator = data;
                    this.roleArr = data.rows;
                    this.preparePageList(+this.pageCreator.beginPage,
                        +this.pageCreator.endPage);
                    this.totalPages = +data.totalPages;
                },
                (error) => {
                    console.error(error)
                });
    };

    prevPage() {
        this.pageNumber = this.pageNumber - 1;
        this.getRolesByPageNum(this.pageNumber)
    }

    nextPage() {
        this.pageNumber = this.pageNumber + 1;
        this.getRolesByPageNum(this.pageNumber)
    }

    emptyArray() {
        while (this.pageList.length) {
            this.pageList.pop();
        }
    }

    preparePageList(start: number, end: number) {
        for (let i = start; i <= end; i++) {
            this.pageList.push(i);
        }
    }

    sortBy(name: string) {
        console.log('sorted by ', name);
        this.order = !this.order;
        console.log('order by asc', this.order);
        this.emptyArray();
        this.roleService.getAllRolesSorted(this.pageNumber, name, this.order)
            .subscribe((data) => {
                    this.pageCreator = data;
                    this.roleArr = data.rows;
                    this.preparePageList(+this.pageCreator.beginPage,
                        +this.pageCreator.endPage);
                    this.totalPages = +data.totalPages;
                },
                (error) => {
                    console.error(error)
                });
    }

}