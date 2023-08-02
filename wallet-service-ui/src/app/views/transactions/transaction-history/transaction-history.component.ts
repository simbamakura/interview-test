import {Component, Input, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {NgbActiveModal, NgbDateStruct} from "@ng-bootstrap/ng-bootstrap";
import {catchError, Observable, throwError} from "rxjs";
import {AccountDto, ResponseDto} from "../../../common";
import {AccountTransactionDto} from "../../../common/model/account-transaction-dto";
import {LoadingService} from "../../../common/service/loading.service";
import {MessagesService} from "../../../common/messages/messages.service";
import {AccountBalanceHttpService} from "../../balances/services/account-balance-http.service";

@Component({
  selector: 'app-transaction-history',
  templateUrl: './transaction-history.component.html',
  styleUrls: ['./transaction-history.component.scss'],
  providers: [MessagesService, LoadingService]
})
export class TransactionHistoryComponent implements OnInit {

  @Input() data: {
    dto: AccountDto;
  };

  searchForm: FormGroup;

  accountsTransactions$: Observable<ResponseDto<AccountTransactionDto[]>>;

  constructor(public activeModal: NgbActiveModal,
              public loadingService: LoadingService,
              private messagesService: MessagesService,
              private accountBalanceService: AccountBalanceHttpService,
              private fb: FormBuilder) {
  }

  ngOnInit(): void {
    this.searchForm = this.fb.group({
      startDate: null,
      endDate: null
    });
  }

  loadTransactions() {
    const startDateStruct: NgbDateStruct = this.searchForm?.get('startDate')?.value;
    const endDateStruct: NgbDateStruct = this.searchForm?.get('endDate')?.value;
    const transactions$ = this.accountBalanceService.getAccountTransactions(
      this.data.dto.id,
      `${startDateStruct.year}-${startDateStruct.month}-${startDateStruct.day}`,
      `${endDateStruct.year}-${endDateStruct.month}-${endDateStruct.day}`)
      .pipe(
        catchError(err => {
          const message = 'Could not Fetch Account Balances';
          this.messagesService.showErrors(message);
          console.log(message, err);
          return throwError(err);
        }),
      );
    this.accountsTransactions$ = this.loadingService.showLoaderUntilCompleted(transactions$);
  }

  onStartDateSelection(date: NgbDateStruct) {
    if (date) {
      this.searchForm.get('startDate')?.setValue(date);

    }
  }

  onEndDateSelection(date: NgbDateStruct) {
    if (date) {
      this.searchForm.get('endDate')?.setValue(date);
    }
  }
}
