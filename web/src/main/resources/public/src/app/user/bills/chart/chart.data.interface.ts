export interface PercentageChartData {
    totalPercentagePaid: number;
    totalPercentageDebt: number;
}

export interface IBarChart{
    month:string;
    totalPaid:number;
    totalUnPaid:number;
}

export interface  BarChartData {
    years: number[];
    innerBarChart: IBarChart[];

}