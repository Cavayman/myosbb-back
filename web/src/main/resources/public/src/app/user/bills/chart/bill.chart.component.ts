import {Component, OnInit} from "@angular/core";
import {CORE_DIRECTIVES, FORM_DIRECTIVES} from "@angular/common";
import {CHART_DIRECTIVES} from "ng2-charts/ng2-charts";
import {PercentageChartData, BarChartData, IBarChart} from "./chart.data.interface";
import {BillChartService} from "./bill.chart.service.component";
import {TranslatePipe} from "ng2-translate";
import {CapitalizeFirstLetterPipe} from "../../../../shared/pipes/capitalize-first-letter";
import {SELECT_DIRECTIVES} from "ng2-select";
import {BillService} from "../bill.service";
import {BillDTO} from "../show_bill_dto.interface";

@Component({
    selector: 'bill-chart',
    templateUrl: 'src/app/user/bills/chart/bill.chart.html',
    providers: [BillChartService, BillService],
    directives: [CORE_DIRECTIVES, SELECT_DIRECTIVES, FORM_DIRECTIVES, CHART_DIRECTIVES],
    pipes: [TranslatePipe, CapitalizeFirstLetterPipe],
    inputs: ['bills']
})
export class BillChartComponent implements OnInit {
    public percentageChartData: PercentageChartData = {totalPercentagePaid: 0, totalPercentageDebt: 0};
    public showPercentageChartLabels: string[] = ['% оплачених', '% заборгованих'];
    public showPercentageChartData: number[] = [];
    public percentageChartType = 'pie';
    public barChartType: string = 'bar';
    public barChartLegend: boolean = true;
    private years: Array<number> = [];
    private bills: BillDTO [] = [];

    public barChartData: BarChartData = {years: null, innerBarChart: null};
    public showBarChartLabels: string[] = [];
    public showBarChartData: any[] = [
        {data: [], label: this.showPercentageChartLabels[0]},
        {data: [], label: this.showPercentageChartLabels[1]}
    ];
    private currentYear: number;

    public barChartOptions: any = {
        scaleShowVerticalLines: false,
        responsive: true
    };

    constructor(private _billChartService: BillChartService,
                private _billService: BillService) {
        this.currentYear = new Date().getFullYear();
        console.log('todays date ' + this.currentYear);

    }

    private fetchBarChartData() {
        this._billChartService.getBarChartData(this.currentYear)
            .subscribe((data)=> {
                    this.barChartData = data;
                    console.log('bill chart data: ' + JSON.stringify(this.barChartData));
                    this.years = data.years;
                    this.showBarChartLabels = this.fillInBarChartLabels(this.barChartData.innerBarChart);
                    this.showBarChartData[0].data = this.fillInPaidBillData(this.barChartData.innerBarChart);
                    this.showBarChartData[1].data = this.fillInUnPaidBillData(this.barChartData.innerBarChart);
                },
                (error)=> {
                    console.log(error)
                })
    }

    refreshBillChartData() {
        this.fetchPieBillChartData();
    }

    ngOnInit(): any {
        this.fetchBarChartData();
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

    public chartPieClicked(e: any): void {
        console.log(e);
    }

    public chartPieHovered(e: any): void {
        console.log(e);
    }

    public chartBarClicked(e: any): void {
        console.log(e);
        this.fetchBarChartData();
    }

    public chartBarHovered(e: any): void {
        console.log(e);
    }
    refreshYear(value: any) {

    }

    selectByYear(value: any) {
        console.log('select year: ' + value.text);
        this.currentYear = +value.id;
        this.fetchBarChartData();
    }

    private fillInBarChartLabels(innerBarCharts: IBarChart[]): string[] {
        let monthsData = [];
        for (let _barChart of innerBarCharts) {
            monthsData.push(_barChart.month);
        }
        return monthsData;
    }

    private fillInPaidBillData(innerBarChart: IBarChart[]): number[] {
        let tempBarChartData: number[] = [];
        for (let _barChart of innerBarChart) {
            tempBarChartData.push(_barChart.totalPaid);
        }
        return tempBarChartData;
    }

    private fillInUnPaidBillData(innerBarChart: IBarChart[]):number[] {
        let tempBarChartData: number[] = [];
        for (let _barChart of innerBarChart) {
            tempBarChartData.push(_barChart.totalUnPaid);
        }
        return tempBarChartData;
    }
}