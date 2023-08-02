import {Component, OnInit, Input} from '@angular/core';
import {AccountDto} from "../../../common";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {NgbActiveModal} from "@ng-bootstrap/ng-bootstrap";
import {MessagesService} from "../../../common/messages/messages.service";
import {LoadingService} from "../../../common/service/loading.service";
import {ToastrService} from "ngx-toastr";
import {ConfirmationDataHolderService} from "../../balances/services/confirmation-data-holder.service";

@Component({
  selector: 'app-send-funds',
  templateUrl: './send-funds.component.html',
  styleUrls: ['./send-funds.component.scss'],
  providers: [MessagesService, LoadingService]
})
export class SendFundsComponent implements OnInit {

  @Input() data: {
    dto: AccountDto | null;
  };
  form: FormGroup;

  constructor(public activeModal: NgbActiveModal,
              private fb: FormBuilder,
              public loadingService: LoadingService,
              private messagesService: MessagesService,
              private toastr: ToastrService,
              private confirmationData: ConfirmationDataHolderService) {
  }

  ngOnInit(): void {
    this.form = this.fb.group({
      amount: ['', Validators.required],
      destinationWalletId: ['', Validators.required]
    });
  }

  onSubmit() {
    this.confirmationData.destinationWalletId = this.form?.get('destinationWalletId')?.value;
    this.confirmationData.amount = this.form?.get('amount')?.value;
    this.confirmationData.sourceAccount = this.data.dto;
    this.confirmationData.sourceWalletId = this.data.dto?.walletId;
    this.activeModal.close(true);
  }
}
