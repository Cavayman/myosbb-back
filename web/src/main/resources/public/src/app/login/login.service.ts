import {Injectable} from "@angular/core";
import {Http,Response} from "@angular/http";
import {Observable} from "rxjs/Observable";
import {User} from "../../shared/models/User";
import ApiService = require("../../shared/services/api.service");

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
        return this.http.post(tokenUrl, JSON.stringify(model));
    }

    //sends token to SERVERS PROTECTED RESOURCES if THIS ONE WILL PASS EVERYTHING IS WORKING
    sendToken(): Observable<any> {
        let userUrl = this._pathUrl + "/restful/user/getCurrent";
        return this.http.get(userUrl);
    }

    private extractData(res: Response) {
        let body = res.json();
        return body.data || {};
    }

    //cheking is there in localstorage data
    checkLogin(): boolean {
      if ((localStorage.getItem("id_token") != null) && (localStorage.getItem("id_token") != "")) {
                return true;
            }
            else
                return false;
    }

    //erasing everything from  local storage
    logOut() {
        localStorage.setItem("id_token", "");
        localStorage.setItem("currentUserName", "");
        alert("You just log out");
    }

    //gets UserName from  localStorage
    getUserName() {
        return localStorage.getItem("currentUserName");
    }
}
