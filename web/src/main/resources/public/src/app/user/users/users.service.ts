import {Injectable} from '@angular/core'
import {User} from "./User";
import {HTTP_PROVIDERS, Http,Headers,Response} from "@angular/http";
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/toPromise';

@Injectable()
export class UsersService {
    private _pathUrl='http://localhost:52430/restful/user/';
    constructor( private http:Http){
    }

    getAllUsers():Observable<any>{
        console.log('in service');
        let headers = new Headers();
        headers.append('Content-Type', 'application/json');
        return this.http.get(this._pathUrl,{headers:headers})
            .map(response => response.json());
    }

    updateUser(user:User):Observable<User>{
        let headers = new Headers();
        headers.append('Content-Type', 'application/json');
        return this.http.post(this._pathUrl+user.userId,JSON.stringify(user),{headers:headers})
            .map((res:Response) => {return new User(res.json())});
    }

    deleteUser(user:User){
        return this.http.delete(this._pathUrl+user.userId)
            .map(response => response.json());
    }

    saveUser(user:User):Observable<User>{
        let headers = new Headers();
        headers.append('Content-Type', 'application/json');
        return this.http.post(this._pathUrl,JSON.stringify(user),{headers:headers})
            .map((res:Response) => {return new User(res.json())});


    }
}