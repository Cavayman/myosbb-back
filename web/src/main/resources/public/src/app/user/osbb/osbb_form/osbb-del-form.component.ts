import { Component, Output, Input, EventEmitter, OnInit, ViewChild } from '@angular/core';
import {CORE_DIRECTIVES} from '@angular/common';
import {IOsbb, Osbb} from "../../../../shared/models/osbb";
import {MODAL_DIRECTIVES, BS_VIEW_PROVIDERS} from 'ng2-bootstrap/ng2-bootstrap';
import {ModalDirective} from "ng2-bootstrap/ng2-bootstrap";
import {TranslatePipe} from "ng2-translate";
import {CapitalizeFirstLetterPipe} from "../../../../shared/pipes/capitalize-first-letter";

@Component({
    selector: 'osbb-del-form',
    templateUrl: './src/app/user/osbb/osbb_form/osbb-del-form.html',
    directives:[MODAL_DIRECTIVES, CORE_DIRECTIVES],
    viewProviders: [BS_VIEW_PROVIDERS],
    pipes: [TranslatePipe, CapitalizeFirstLetterPipe]
})
export class OsbbDelFormComponent {
    @Output() delete: EventEmitter<IOsbb>;
    private osbb: IOsbb;
    @ViewChild('delModal')
    delModal:ModalDirective;

    openDelModal(osbbId:IOsbb): void {
        this.osbb = osbbId;
        this.delModal.show();   
    }

    constructor() {
        this.delete = new EventEmitter<IOsbb>();
    }

   delOsbb(): void {
        this.delete.emit(this.osbb);
    }
}