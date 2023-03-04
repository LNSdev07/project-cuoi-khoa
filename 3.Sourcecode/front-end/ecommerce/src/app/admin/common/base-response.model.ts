export interface BaseReponse<T> {
    data: T;
    message: string;
    status: number;
    totalRecords: number;
}