import {Injectable} from '@angular/core';
import {HTTP_PROVIDERS, Http, Headers} from "@angular/http";
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/toPromise';

import {IRole} from './role';

@Injectable()
export class RoleService { 

    private deleteUrl:string = 'http://localhost:52430/restful/role/id/';
    private postUrl:string = 'http://localhost:52430/restful/role';
    private putUrl:string = 'http://localhost:52430/restful/role';
    private getUrl:string = 'http://localhost:52430/restful/role';
    

    constructor(private http: Http) { }

    getAllRole(): Promise<IRole[]> {
        return this.http.get(this.getUrl)
                 .toPromise()
                 .then(res => res.json())
                 .catch(this.handleError);
    }

    addRole(role:IRole): Promise<IRole> {
        let headers = new Headers({'Content-Type': 'application/json' });
        return this.http.post(this.postUrl, JSON.stringify(role), {headers})
                        .toPromise()
                        .then(res => res.json())
                        .catch(this.handleError);
    }

    editRole(role:IRole):Promise<IRole>  {
        let headers = new Headers({'Content-Type': 'application/json' });
        return this.http.put(this.putUrl, JSON.stringify(role), {headers})
                        .toPromise()
                        .then(res => res.json())
                        .catch(this.handleError);
    }

    deleteRole(role:IRole): Promise<IRole> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let url = ` ${this.deleteUrl}/${role.roleId}`;
        return this.http.delete(url,{headers})
                    .toPromise()
                    .then(res => role)
                    .catch(this.handleError);
    }

    private handleError(error: any):Promise<any> {
        console.log('HandleError', error);
        return Promise.reject(error.message || error);
    }
}