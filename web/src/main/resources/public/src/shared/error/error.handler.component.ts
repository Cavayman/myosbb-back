import {Component} from "@angular/core";
import {TranslatePipe} from "ng2-translate";
import {Toast, BodyOutputType} from "angular2-toaster/angular2-toaster";


export let onErrorResourceNotFoundToastMsg: Toast = {
    type: 'error',
    title: '',
    body: '<h5>Виникла помилка під час завантаження ресурсу</h5>',
    showCloseButton: true,
    bodyOutputType: BodyOutputType.TrustedHtml
};
export let onErrorServerNoResponseToastMsg: Toast = {
    type: 'error',
    title: '',
    body: '<h5>Сервер не відповідає</h5>',
    showCloseButton: true,
    bodyOutputType: BodyOutputType.TrustedHtml
};

@Component({
    selector: 'my-error',
    templateUrl: 'src/shared/error/404.html',
    pipes: [TranslatePipe]
})
export class ErrorHandlerComponent {

}