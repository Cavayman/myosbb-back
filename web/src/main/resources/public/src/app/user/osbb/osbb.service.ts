import {Injectable} from "@angular/core";
import {Http, Headers} from "@angular/http";

import "rxjs/add/operator/map";
import "rxjs/add/operator/toPromise";

import {IOsbb} from "../../../shared/models/osbb";
import ApiService = require("../../../shared/services/api.service");

@Injectable()
export class OsbbService { 

    private url:string = ApiService.serverUrl + '/restful/osbb';

    constructor(private http: Http) { 
    }

    getAllOsbb(): Promise<IOsbb[]> {
        return this.http.get(this.url)
                 .toPromise()
                 .then(res => res.json())
                 .catch(this.handleError);
    }
    
    getAllOsbbByNameContaining(osbbName: string ):Promise<IOsbb[]> {
        let url = this.url + '/search/' + osbbName;
        return this.http.get(url)
                 .toPromise()
                 .then(res => res.json())
                 .catch(this.handleError);
    }

    getAllOsbbByOrder(field: string, order: boolean) {
        let url = this.url + '/order/' + field + ',' + order;
        return this.http.get(url)
                 .toPromise()
                 .then(res => res.json())
                 .catch(this.handleError);
    }

    getOsbbById(osbbId: number): Promise<IOsbb> {
         let url = this.url + "/" + osbbId;
         return this.http.get(url)
                 .toPromise()
                 .then(res => res.json())
                 .catch(this.handleError);
    }

    addOsbb(osbb:IOsbb): Promise<IOsbb> {
        return this.http.post(this.url, JSON.stringify(osbb))
                        .toPromise()
                        .then(res => res.json())
                        .catch(this.handleError);
    }

    editOsbb(osbb:IOsbb):Promise<IOsbb>  {
        return this.http.put(this.url, JSON.stringify(osbb))
                        .toPromise()
                        .then(res => res.json())
                        .catch(this.handleError);
    }

    deleteOsbb(osbb:IOsbb): Promise<IOsbb> {
        return this.http.delete(this.url + '/' + osbb.osbbId)
                    .toPromise()
                    .then(res => osbb)
                    .catch(this.handleError);
    }

    private handleError(error: any):Promise<any> {
        console.log('HandleError', error);
        return Promise.reject(error.message || error);
    }
}