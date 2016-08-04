import {Component, OnInit, OnDestroy, ViewChild} from "@angular/core";
import {CORE_DIRECTIVES} from "@angular/common";
import {Attachment} from "./attachment.interface";
import {AttachmentService} from "./attachment.service";
import {PageCreator} from "../../../shared/services/page.creator.interface";
import "rxjs/Rx";
import {MODAL_DIRECTIVES, BS_VIEW_PROVIDERS, ModalDirective} from "ng2-bootstrap/ng2-bootstrap";

@Component({
    selector: 'my-attachment',
    templateUrl: 'src/app/user/attachment/attachment.html',
    providers: [AttachmentService],
    directives: [MODAL_DIRECTIVES, CORE_DIRECTIVES],
    viewProviders: [BS_VIEW_PROVIDERS]
})
export class UserAttachmentComponent implements OnInit, OnDestroy {

    private attachments:Attachment[];
    private selectedAttachment:Attachment = {attachmentId: null, path: ''};
    private newAttachment:Attachment = {attachmentId: null, path: ''};
    private pageCreator:PageCreator<Attachment>;
    private pageNumber:number = 1;
    private pageList:Array<number> = [];
    private totalPages:number;
    @ViewChild('delModal') public delModal:ModalDirective;
    @ViewChild('delAllModal') public delAllModal:ModalDirective;
    @ViewChild('createModal') public createModal:ModalDirective;
    @ViewChild('editModal') public editModal:ModalDirective;
    active:boolean = true;
    order:boolean = true;

    private attachmentId:number;

    constructor(private _attachmentService:AttachmentService) {
    }

    openEditModal(attachment:Attachment) {
        this.selectedAttachment = attachment;
        console.log('selected attachment: ' + this.selectedAttachment);
        this.editModal.show();
    }

    isDateValid(date:string):boolean {
        return /\d{4}-\d{2}-\d{2}/.test(date);
    }

    onEditAttachmentSubmit() {
        this.active = false;
        console.log('saving attachment: ' + this.selectedAttachment);
        this._attachmentService.editAndSave(this.selectedAttachment);
        this._attachmentService.getAllAttachments(this.pageNumber);
        this.editModal.hide();
        setTimeout(() => this.active = true, 0);
    }

    closeEditModal() {
        console.log('closing edit modal');
        this.editModal.hide();
    }

    openCreateModal() {
        this.createModal.show();
    }

    onCreateAttachmentSubmit() {
        this.active = false;
        console.log('creating attachment');
        this._attachmentService.addAttachment(this.newAttachment);
        this._attachmentService.getAllAttachments(this.pageNumber);
        this.getAttachmentsByPageNum(this.pageNumber);
        this.createModal.hide();
        setTimeout(() => this.active = true, 0);
    }

    closeCreateModal() {
        console.log('closing create modal');
        this.createModal.hide();
    }

    openDelModal(id:number) {
        this.attachmentId = id;
        console.log('show', this.attachmentId);
        this.delModal.show();
    }

    closeDelModal() {
        console.log('delete', this.attachmentId);
        this._attachmentService.deleteAttachmentById(this.attachmentId);
        this.getAttachmentsByPageNum(this.pageNumber);
        this.delModal.hide();
    }

    openDelAllModal() {
        this.delAllModal.show();
    }

    closeDelAllModal() {
        console.log('delete all');
        this._attachmentService.deleteAllAttachments();
        this.getAttachmentsByPageNum(this.pageNumber);
        this.delAllModal.hide();
    }

    ngOnInit():any {
        this.getAttachmentsByPageNum(this.pageNumber);
    }

    getAttachmentsByPageNum(pageNumber:number) {
        this.pageNumber = +pageNumber;
        this.emptyArray();
        return this._attachmentService.getAllAttachments(this.pageNumber)
            .subscribe((data) => {
                    this.pageCreator = data;
                    this.attachments = data.rows;
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
        this.getAttachmentsByPageNum(this.pageNumber)
    }

    nextPage() {
        this.pageNumber = this.pageNumber + 1;
        this.getAttachmentsByPageNum(this.pageNumber)
    }

    emptyArray() {
        while (this.pageList.length) {
            this.pageList.pop();
        }
    }

    preparePageList(start:number, end:number) {
        for (let i = start; i <= end; i++) {
            this.pageList.push(i);
        }
    }


    sortBy(name:string) {
        console.log('sorted by ', name);
        this.order = !this.order;
        console.log('order by asc', this.order);
        this.emptyArray();
        this._attachmentService.getAllAttachmentsSorted(this.pageNumber, name, this.order)
            .subscribe((data) => {
                    this.pageCreator = data;
                    this.attachments = data.rows;
                    this.preparePageList(+this.pageCreator.beginPage,
                        +this.pageCreator.endPage);
                    this.totalPages = +data.totalPages;
                },
                (error) => {
                    console.error(error)
                });
    }


    ngOnDestroy():any {
        //this.subscriber.unsubscribe();
    }

}