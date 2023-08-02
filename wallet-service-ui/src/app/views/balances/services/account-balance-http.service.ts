import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {AccountDto, ResponseDto, TransferFundsRequestDto, Util} from "../../../common";
import {Observable, shareReplay} from "rxjs";
import {environment} from "../../../../environments/environment";
import {AccountTransactionDto} from "../../../common/model/account-transaction-dto";
import {QueryParams} from "@ngrx/data";

@Injectable({
  providedIn: 'root'
})
export class AccountBalanceHttpService {

  constructor(private http: HttpClient) {
  }

  fetchUserAccountBalances(userId: number): Observable<ResponseDto<AccountDto[]>> {
    const headers = Util.getHeaders();
    return this.http.get<ResponseDto<AccountDto[]>>(environment.baseUrl +
      environment.accountBalanceUrl + '/' + userId, {
      headers
    }).pipe(shareReplay());
  }

  transfer(request: TransferFundsRequestDto): Observable<ResponseDto<AccountTransactionDto>> {
    const headers = Util.getHeaders();
    return this.http.post<ResponseDto<AccountTransactionDto>>(environment.baseUrl +
      environment.transfer, request, {
      headers
    }).pipe(shareReplay());
  }


  getAccountByWallet(walletId: string): Observable<ResponseDto<AccountDto>> {
    const headers = Util.getHeaders();
    return this.http.get<ResponseDto<AccountDto>>(environment.baseUrl +
      environment.accountBalanceUrl + '/' + environment.getWalletBalance + '/' + walletId, {
      headers
    }).pipe(shareReplay());
  }

  getAccountTransactions(accountId: string, startDate: string, endDate: string)
    : Observable<ResponseDto<AccountTransactionDto[]>> {
    const headers = Util.getHeaders();
    const params: QueryParams = {accountId, startDate, endDate};
    return this.http.get<ResponseDto<AccountTransactionDto[]>>(environment.baseUrl +
      environment.accountTransactions, {
      params, headers
    }).pipe(shareReplay());
  }
}
