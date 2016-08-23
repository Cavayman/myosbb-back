import {Injectable} from "@angular/core";
import {Http, Headers} from "@angular/http";
import {Observable} from "rxjs/Observable";
import ApiService = require("../../shared/services/api.service");
import {User} from "../../shared/models/User";

@Injectable()
export class LoginService {
    token: string;
    private _pathUrl = ApiService.serverUrl;
    currentUser: User;

    constructor(private http: Http) {
    }

    //Sending credentials{username ,password for getting token}
    sendCredentials(model) {
        let tokenUrl = this._pathUrl + "/user/login";
        let headers = new Headers({ 'Content-Type': 'application/json' });
        return this.http.post(tokenUrl, JSON.stringify(model), { headers: headers });
    }

    //sends token to SERVERS PROTECTED RESOURCES if THIS ONE WILL PASS EVERYTHING IS WORKING
    sendToken(token): Observable<any> {
        let userUrl = this._pathUrl + "/restful/user/getCurrent";
        let headers2 = new Headers({ 'Authorization': 'Bearer ' + token });
        return this.http.get(userUrl, { headers: headers2 });
    }

    private extractData(res: Response) {
        let body = res.json();
        return body.data || {};
    }

    //cheking is there in localstorage data
    checkLogin(): boolean {
        if (localStorage.getItem("token") != null && localStorage.getItem("currentUserName") != null)
            if (localStorage.getItem("currentUserName") != "" && localStorage.getItem("token") != "") {
                return true;
            }
            else
                return false;
    }

    //erasing everything from  local storage
    logOut() {
        localStorage.setItem("token", "");
        localStorage.setItem("currentUserName", "");
        alert("You just log out");
    }

    //gets UserName from  localStorage
    getUserName() {
        return localStorage.getItem("currentUserName");
    }
}
