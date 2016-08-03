import {Component,OnInit} from '@angular/core'


import {userItem} from './userItem.ts';
import {UsersService} from "./users.service";
import {Router} from '@angular/router';
import { REACTIVE_FORM_DIRECTIVES, FormBuilder, Validators } from '@angular/forms';


@Component ({
	selector:'my-users',
	templateUrl:'src/app/user/users/users.table.html',
	providers:[UsersService],
	styleUrls:['src/app/user/users/users.css'],
	directives: [REACTIVE_FORM_DIRECTIVES]
})
export class UsersComponent implements OnInit{
	userList:userItem[];
	userForm: any;

	 constructor(private _userService:UsersService,private router: Router,private formBuilder: FormBuilder){
		 console.log('constructore');
		 this.userList=[];
		 this.userForm = this.formBuilder.group({
			 'name': ['', Validators.required],
			 'email': ['', [Validators.required,]],
			 'profile': ['', [Validators.required, Validators.minLength(10)]]
		 });
	 }
	ngOnInit():any {
		console.log('init');
		this._userService.getAllUsers().subscribe(data => this.userList = data,error=>console.error(error));
     	console.log('get out of service');
	}

	updateUser(user:userItem){
		this._userService.updateUser(user).subscribe(()=>this.router.navigate(['/users']))
	}
	deleteUser(user:userItem){
		this._userService.deleteUser(user)
		this.router.navigate(['/users']);
	}
 	saveUser(form){
		alert(`Name: ${this.userForm.value.name} Email: ${this.userForm.value.email}`);
	}
}