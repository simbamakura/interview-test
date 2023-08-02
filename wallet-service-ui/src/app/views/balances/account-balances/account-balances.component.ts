import {Component, OnInit} from '@angular/core';
import {AccountBalanceHttpService} from "../services/account-balance-http.service";
import {catchError, Observable, throwError} from "rxjs";
import {AccountDto, ResponseDto} from "../../../common";
import {LoadingService} from "../../../common/service/loading.service";
import {MessagesService} from "../../../common/messages/messages.service";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {SendFundsComponent} from "../../transactions/send-funds/send-funds.component";
import {
  SendFundsConfirmationComponent
} from "../../transactions/send-funds-confirmation/send-funds-confirmation.component";
import {TransactionHistoryComponent} from "../../transactions/transaction-history/transaction-history.component";

@Component({
  selector: 'app-account-balances',
  templateUrl: './account-balances.component.html',
  styleUrls: ['./account-balances.component.scss'],
  providers: [LoadingService, MessagesService]
})
export class AccountBalancesComponent implements OnInit {

  accounts$: Observable<ResponseDto<AccountDto[]>>;

  constructor(private balanceService: AccountBalanceHttpService,
              private modalService: NgbModal,
              private messagesService: MessagesService,
              private loadingService: LoadingService) {
  }

  ngOnInit(): void {
    this.loadData();
  }

  private loadData() {
    const balanceEntity$ = this.balanceService.fetchUserAccountBalances(1).pipe(
      catchError(err => {
        const message = 'Could not Fetch Account Balances';
        this.messagesService.showErrors(message);
        console.log(message, err);
        return throwError(err);
      }),
    );
    this.accounts$ = this.loadingService.showLoaderUntilCompleted(balanceEntity$);
  }

  sendMoney(account: AccountDto): void {
    const modalRef = this.modalService.open(SendFundsComponent);
    modalRef.componentInstance.data = {};
    modalRef.componentInstance.data.dto = account;
    modalRef.closed.subscribe(value => {
      if (value == true) {
        const modalRef = this.modalService.open(SendFundsConfirmationComponent);
        modalRef.closed.subscribe(result => {
          if (value == true) {
            this.loadData();
          }
        })
      }
    });
  }

  viewTransactions(account: AccountDto) {
    const modalRef = this.modalService.open(TransactionHistoryComponent, {size: 'xl'});
    modalRef.componentInstance.data = {};
    modalRef.componentInstance.data.dto = account;
  }
}
