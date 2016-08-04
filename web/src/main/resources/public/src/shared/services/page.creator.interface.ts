export interface PageCreator<T> {
    currentPage:string,
    totalPages:string,
    beginPage:string,
    endPage:string,
    rows:T[]

}

