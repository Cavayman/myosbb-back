import {Component,OnInit} from '@angular/core'


import {userItem} from './userItem.ts';
import {UsersService} from "./users.service";
import {Router} from '@angular/router';

@Component ({
	selector:'my-users',
	templateUrl:'src/app/user/users/users.table.html',
	providers:[UsersService],
	styleUrls:['src/app/user/users/users.css']
})
export class UsersComponent implements OnInit{
	userList:userItem[];

	 constructor(private _userService:UsersService,private router: Router){
		 console.log('constructore');
		 this.userList=[];
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

}