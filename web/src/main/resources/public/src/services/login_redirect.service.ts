import {Injectable} from "angular2/core";

@Injectable()
export class LoginRedirectService{

    private _loginSuccess:boolean = false;

    setLoginStatus(status:boolean){
        console.log('redirect status: ', status);
        this._loginSuccess = status;
    }

    getRedirectStatus(){
        return this._loginSuccess;
    }


}
