import {Component} from '@angular/core';
import {ROUTER_DIRECTIVES} from '@angular/router'

@Component({
    selector: 'osbb-header',
    templateUrl: 'src/app/home/header/header.html',
    styleUrls: ['src/app/home/header/header.css'],
    directives: [ROUTER_DIRECTIVES]
})
export class HeaderComponent {
}