/**
 * Created by Anastasiia Fedorak  8/2/16.
 */
import {Injectable} from "@angular/core";
import {Http, Headers, Response} from "@angular/http";
import {Observable} from "rxjs/Rx";
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/toPromise';
import {Contract} from "../../../../shared/models/contract.interface";
import ApiService = require("../../../../shared/services/api.service");

@Injectable()
export class ContractService {

    private url = ApiService.serverUrl + '/restful/contract/';
    private urlWithParams = ApiService.serverUrl + '/restful/contract?pageNum=';

    constructor(private _http:Http){
    }

    getContracts(pageNumber:number) : Observable<any>{
        console.log("get contracts inside service, pagenum" + pageNumber);
        console.log("sending http GET to " +this.urlWithParams + pageNumber);
        return  this._http.get(this.urlWithParams + pageNumber)
            .map(res => res.json())
            .catch((err)=>Observable.throw(err));
    }
    getContractsByState(pageNumber:number, onlyActive : boolean) : Observable<any>{
        console.log("get only active contracts inside service, pagenum" + pageNumber);
        console.log("sending http GET to " +this.urlWithParams + pageNumber  + '&&actv=' + onlyActive);
        return  this._http.get(this.urlWithParams + pageNumber + '&&actv=' + onlyActive)
            .map(res => res.json())
            .catch((err)=>Observable.throw(err));
    }

    getContractById(id: number) : Observable<any> {
        console.log("ok");
        return this._http.get(this.url + id)
            .map(res => res.json().data,
                    err => console.log(err));
    }

    getSortedContracts(pageNumber:number, name:string, order:boolean):Observable<any> {
        return this._http.get(this.urlWithParams + pageNumber + '&&sortedBy=' + name + '&&asc=' + order)
            .map((res)=> res.json())
            .catch((err)=>Observable.throw(err));
    }
    getSortedActiveContracts(pageNumber:number, name:string, order:boolean, onlyActive:boolean):Observable<any> {
        return this._http.get(this.urlWithParams + pageNumber + '&&sortedBy=' + name + '&&asc=' + order + '&&actv=' +
            onlyActive)
            .map((res)=> res.json())
            .catch((err)=>Observable.throw(err));
    }

    deleteContractById(contractId:number) {
        let url = this.url + contractId;
        console.log('delete contract by id: ' + contractId);
        console.log("sending http DELETE to " +url);
        return this._http.delete(url)
            .map(res => res.json())
            .catch((err)=>Observable.throw(err))
    }

    editAndSave(contract:Contract) {
            console.log('updating contract with id: ' + contract.contractId);
            console.log("sending http POST to " +this.url + contract.contractId);
             console.log("json obj: " + JSON.stringify(contract));
             return this._http.post(this.url + contract.contractId, JSON.stringify(contract))
            .map(res => res.json())
            .catch((err)=>Observable.throw(err))
    }

    addContract(contract:Contract) {
        console.log("sending http POST to " +this.url);
        return this._http.post(this.url, JSON.stringify(contract))
            .map(res => res.json())
            .catch((err)=>Observable.throw(err))
    }

    findByProviderName(search: string) :  Observable<any>{
        console.log("searching contracts");
        console.log("param is" + search);
        return  this._http.get(this.url + "find?name="+search)
            .map(res => res.json())
            .catch((err)=>Observable.throw(err));
    }
}