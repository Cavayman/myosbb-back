import {Injectable} from "@angular/core";
import {User} from "../../shared/models/User";
import {Response} from "@angular/http";
@Injectable()
export class CurrentUserService {

    private currentUser:User=new User();


    setUser(user:Response) {
        this.currentUser=<User>user.json();
    }
    
    getUser():User{
        return this.currentUser;
    }

}