import {Component, OnInit} from '@angular/core'
import {User} from '../../../shared/models/User.ts';
import {Router} from '@angular/router';
import {REACTIVE_FORM_DIRECTIVES, FormBuilder, Validators} from '@angular/forms';
import {CurrentUserService} from "../../../shared/services/current.user.service";

@Component({
    selector: 'my-user-profile',
    templateUrl: 'src/app/user/profile/profile.html',
    directives: [REACTIVE_FORM_DIRECTIVES],
    styleUrls: ['src/app/user/profile/profile.css'],
   
})
export class ProfileComponent implements OnInit {
    currentUser:User;

    constructor(private router:Router,private _currentUser:CurrentUserService) {
        console.log('constructore');
        this.currentUser=_currentUser.getUser();
    }


}