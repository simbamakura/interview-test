import {Component, OnInit} from '@angular/core';
import {NgbActiveModal} from "@ng-bootstrap/ng-bootstrap";
import {FormBuilder} from "@angular/forms";
import {LoadingService} from "../../../common/service/loading.service";
import {MessagesService} from "../../../common/messages/messages.service";
import {ToastrService} from "ngx-toastr";
import {ConfirmationDataHolderService} from "../../balances/services/confirmation-data-holder.service";
import {AccountBalanceHttpService} from "../../balances/services/account-balance-http.service";
import {catchError, Observable, throwError} from "rxjs";
import {AccountDto, ResponseDto, TransferFundsRequestDto} from "../../../common";

@Component({
  selector: 'app-send-funds-confirmation',
  templateUrl: './send-funds-confirmation.component.html',
  styleUrls: ['./send-funds-confirmation.component.scss'],
  providers: [MessagesService, LoadingService]
})
export class SendFundsConfirmationComponent implements OnInit {

  accounts$: Observable<ResponseDto<AccountDto>>;

  constructor(public activeModal: NgbActiveModal,
              private fb: FormBuilder,
              public loadingService: LoadingService,
              private messagesService: MessagesService,
              private toastr: ToastrService,
              public confirmationData: ConfirmationDataHolderService,
              private accountService: AccountBalanceHttpService) {
  }

  ngOnInit(): void {
    const accountEntity$ = this.accountService.getAccountByWallet(this.confirmationData.destinationWalletId).pipe(
      catchError(err => {
        const message = 'Could not Fetch Account Balances';
        this.messagesService.showErrors(message);
        console.log(message, err);
        return throwError(err);
      }),
    );
    this.accounts$ = this.loadingService.showLoaderUntilCompleted(accountEntity$);
    this.accounts$.subscribe(value => this.confirmationData.destinationAccount = value.data);
  }

  onSend() {
    const transferRequest: TransferFundsRequestDto = {
      amount: this.confirmationData.amount,
      destinationWalletId: this.confirmationData.destinationAccount?.walletId,
      sourceWalletId: this.confirmationData.sourceWalletId
    }
    const transfer$ = this.accountService.transfer(transferRequest).pipe(
      catchError(err => {
        const message = 'Could not Fetch Account Balances';
        this.messagesService.showErrors(message);
        console.log(message, err);
        return throwError(err);
      }),
    );
    this.loadingService.showLoaderUntilCompleted(transfer$).subscribe(value => {
      this.toastr.info('Transfer was successful',
        'Success');
      this.activeModal.close(true);
    });
  }
}
