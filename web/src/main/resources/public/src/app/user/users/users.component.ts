import {Component, OnInit} from '@angular/core'
import {User} from '../../../shared/models/User.ts';
import {UsersService} from "./users.service";
import {Router} from '@angular/router';
import {REACTIVE_FORM_DIRECTIVES, FormBuilder, Validators} from '@angular/forms';


@Component({
    selector: 'my-users',
    templateUrl: 'src/app/user/users/users.table.html',
    providers: [UsersService],
    styleUrls: ['src/app/user/users/users.css'],
    directives: [REACTIVE_FORM_DIRECTIVES]
})
export class UsersComponent implements OnInit {
    userList:User[];
    userForm:any;

    constructor(private _userService:UsersService, private router:Router, private formBuilder:FormBuilder) {
        console.log('constructore');
        this.userList = [];
        this.userForm = this.formBuilder.group({
            'firstName': ['', Validators.required],
            'lastName': ['', Validators.required],
            'email': ['', [Validators.required,]],
            'phoneNumber': ['', Validators.required],
            'gender': ['', Validators.required],
            'birthDate': ['', Validators.required],
            'password': ['', Validators.required],
        });
    }

    ngOnInit():any {
        console.log('init');
        this._userService.getAllUsers().subscribe(data => this.userList = data, error=>console.error(error));
        console.log('get out of service');
    }

    updateUser(user:User) {
        this._userService.updateUser(user).subscribe(()=>this.router.navigate(['/users']));
    }

    deleteUser(user:User) {
        this._userService.deleteUser(user).subscribe(()=>this.userList.splice(this.userList.indexOf(user,0),1));
    }

    saveUser() {
     let user:User={firstName:this.userForm.value.firstName,
            lastName:this.userForm.value.lastName ,
            email:this.userForm.value.email ,
            phoneNumber:this.userForm.value.phoneNumber,
            gender:this.userForm.value.gender,
            birthDate:this.userForm.value.birthDate,
            password:this.userForm.value.password};
        this._userService.saveUser(user).subscribe((data)=>this.userList.push(data));
    }
}