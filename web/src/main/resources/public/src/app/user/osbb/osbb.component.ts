import {Component, OnInit} from '@angular/core';
import {CORE_DIRECTIVES} from '@angular/common';
import {HTTP_PROVIDERS} from "@angular/http";
import {MODAL_DIRECTIVES, BS_VIEW_PROVIDERS} from 'ng2-bootstrap/ng2-bootstrap';
import {ModalDirective} from "ng2-bootstrap/ng2-bootstrap";
import {Observable} from 'rxjs/Observable';
import 'rxjs/Rx';

import {IOsbb, Osbb} from "../../../shared/models/osbb";
import { OsbbService } from './osbb.service';
import { OsbbAddFormComponent } from './osbb_form/osbb-add-form.component';
import { OsbbEditFormComponent } from './osbb_form/osbb-edit-form.component';
import { OsbbDelFormComponent } from './osbb_form/osbb-del-form.component';
import {CurrentUserService} from "../../../shared/services/current.user.service";

@Component({
    selector: 'osbb',
    templateUrl: './src/app/user/osbb/osbb.component.html',
     providers: [HTTP_PROVIDERS, OsbbService],
    directives: [MODAL_DIRECTIVES, CORE_DIRECTIVES, OsbbAddFormComponent, OsbbEditFormComponent, OsbbDelFormComponent],
    viewProviders: [BS_VIEW_PROVIDERS]
})
export class OsbbComponent implements OnInit { 
    
    osbbArr:IOsbb[];
    updatedOsbb:IOsbb;

    constructor( private osbbService: OsbbService, private userService:CurrentUserService) { 
        this.osbbArr = [];
    }

    ngOnInit() {
        this.osbbService.getAllOsbb().then(osbbArr => this.osbbArr = osbbArr);
    }

    private initUpdatedOsbb(osbb:IOsbb): void {
        this.updatedOsbb = osbb;
    }

    createOsbb(osbb:IOsbb): void {
        this.osbbService.addOsbb(osbb).then(osbb => this.addOsbb(osbb));
    }

    editOsbb(osbb:IOsbb): void {
        this.osbbService.editOsbb(osbb);
    }

    deleteOsbb(osbb:IOsbb): void {
        this.osbbService.deleteOsbb(osbb).then(osbb => this.deleteOsbbFromArr(osbb));
    }

    private addOsbb(osbb: IOsbb): void {
        this.osbbArr.push(osbb);
    }

     private deleteOsbbFromArr(osbb: IOsbb): void {
         let index = this.osbbArr.indexOf(osbb);
         if(index > -1) {
            this.osbbArr.splice(index, 1);
         }
    }

    getCreationDate(date:Date):string {
        return new Date(date).toLocaleString();
    }

    printCurrentUser() {
        console.log("CurrentUserId: " + this.userService.getUser().userId);
    }

    searchByNameOsbb(osbbName: string) {
        if(osbbName.trim()!=='') {
            this.osbbService.getAllOsbbByNameContaining(osbbName).then(osbbArr => this.osbbArr = osbbArr);
        } else {
             this.osbbService.getAllOsbb().then(osbbArr => this.osbbArr = osbbArr);
        }
    }
}