import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AccountBalancesComponent } from './balances/account-balances/account-balances.component';
import {ViewsroutingModule} from "./viewsrouting.module";

import {
  AvatarModule,
  BadgeModule,
  ButtonGroupModule,
  ButtonModule,
  CardModule,
  FormModule,
  GridModule,
  ListGroupModule,
  ModalModule,
  NavModule,
  PopoverModule,
  ProgressModule,
  SpinnerModule,
  TableModule,
  TabsModule,
  DropdownModule,
  SharedModule,
  WidgetModule
} from "@coreui/angular";
import {HttpClientModule} from "@angular/common/http";
import {NgbDatepickerModule, NgbModule, NgbPaginationModule, NgbToastModule} from "@ng-bootstrap/ng-bootstrap";

import {IconModule} from "@coreui/icons-angular";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {MessagesComponent} from "../common/messages/messages.component";
import {SpinnerComponent} from "../common/spinner/spinner.component";
import {SendFundsComponent} from "./transactions/send-funds/send-funds.component";
import {SendFundsConfirmationComponent} from "./transactions/send-funds-confirmation/send-funds-confirmation.component";
import {TransactionHistoryComponent} from "./transactions/transaction-history/transaction-history.component";




@NgModule({
  declarations: [
    AccountBalancesComponent,
    MessagesComponent,
    SpinnerComponent,
    SendFundsComponent,
    SendFundsConfirmationComponent,
    TransactionHistoryComponent
  ],
  imports: [
    ViewsroutingModule,
    NgbDatepickerModule,
    CommonModule,
    CardModule,
    NavModule,
    IconModule,
    CommonModule,
    GridModule,
    ProgressModule,
    NgbModule,
    FormsModule,
    ReactiveFormsModule,
    ButtonModule,
    FormModule,
    ButtonModule,
    ButtonGroupModule,
    AvatarModule,
    TableModule,
    HttpClientModule,
    CardModule,
    IconModule,
    BadgeModule,
    ModalModule,
    NgbToastModule,
    PopoverModule,
    ListGroupModule,
    SpinnerModule,
    TabsModule,
    NgbPaginationModule,
    WidgetModule,
    IconModule,
    DropdownModule,
    SharedModule
  ]
})
export class ViewsModule { }
