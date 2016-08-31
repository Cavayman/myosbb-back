import {Component, OnInit} from "@angular/core";
import {CORE_DIRECTIVES, FORM_DIRECTIVES} from "@angular/common";
import {CHART_DIRECTIVES} from "ng2-charts/ng2-charts";
import {PercentageChartData} from "./chart.data.interface";
import {BillChartService} from "./bill.chart.service.component";
import {TranslatePipe} from "ng2-translate";
import {CapitalizeFirstLetterPipe} from "../../../../shared/pipes/capitalize-first-letter";

@Component({
    selector: 'bill-chart',
    templateUrl: 'src/app/user/bills/chart/bill.chart.html',
    providers: [BillChartService],
    directives: [CORE_DIRECTIVES, FORM_DIRECTIVES, CHART_DIRECTIVES],
    pipes:[TranslatePipe, CapitalizeFirstLetterPipe]
})
export class BillChartComponent implements OnInit {
    public percentageChartData: PercentageChartData = {totalPercentagePaid: 0, totalPercentageDebt: 0};
    public showPercentageChartLabels: string[] = ['% оплачених', '% заборгованих'];
    public showPercentageChartData: number[] = [];
    public percentageChartType = 'pie';

    constructor(private _billChartService: BillChartService) {
    }

    refresh(){
        this.fetchPieBillChartData();
    }

    ngOnInit(): any {
        this.fetchPieBillChartData();
    }

    private fetchPieBillChartData() {
        this._billChartService.getPercentageChartData()
            .subscribe((data)=> {
                    console.log('received: ', JSON.stringify(data));
                    this.percentageChartData = data;
                    this.showPercentageChartData = this.fillPieChartData();
                },
                (error)=> {
                    console.log(error);
                });
    }

    fillPieChartData(): number[] {
        let tempArr = [];
        tempArr.push(this.percentageChartData.totalPercentagePaid);
        tempArr.push(this.percentageChartData.totalPercentageDebt);
        return tempArr;
    }

    public chartClicked(e: any): void {
        console.log(e);
    }

    public chartHovered(e: any): void {
        console.log(e);
    }

}