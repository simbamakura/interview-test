export interface ResponseDto<T> {
  success: boolean;
  narrative: string;
  currentPage: number;
  pageSize: number;
  data: T;
}
