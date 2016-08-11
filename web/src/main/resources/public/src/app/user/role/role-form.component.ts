import {Component, Output, Input, EventEmitter, OnInit, ViewChild} from "@angular/core";
import {MODAL_DIRECTIVES, ModalComponent} from "ng2-bs3-modal/ng2-bs3-modal";
import {IRole, Role} from "./role";

@Component({
    selector: 'role-form',
    templateUrl: './src/app/user/role/role-form.component.html',
    directives:[MODAL_DIRECTIVES]
})
export class RoleFormComponent implements OnInit{
    @Output() created: EventEmitter<Role>;
    @Output() updated: EventEmitter<Role>;
    @Input() role:IRole;
    

    @ViewChild('updateModal')
    updateModal:ModalComponent;

    openUpdateModal() {
        this.updateModal.open();  
    }

    closeUpdateModal() {
        this.updateModal.close();
    }

    constructor() {
        this.created = new EventEmitter<Role>();
        this.updated = new EventEmitter<Role>();
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

    editRole(name:string) {
        if (name) {
           this.role.name = name;
           this.updated.emit(this.role);
        }
    }
}