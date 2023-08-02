import { Injectable } from '@angular/core';
import {AccountDto} from "../../../common";

@Injectable({
  providedIn: 'root'
})
export class ConfirmationDataHolderService {

  private _sourceWalletId: string | undefined;
  private _destinationWalletId: string;
  private _amount: string;
  private _sourceAccount: AccountDto | null;
  private _destinationAccount: AccountDto;


  get sourceAccount(): AccountDto | null {
    return this._sourceAccount;
  }

  set sourceAccount(value: AccountDto | null) {
    this._sourceAccount = value;
  }

  get sourceWalletId(): string | undefined {
    return this._sourceWalletId;
  }

  set sourceWalletId(value: string | undefined) {
    this._sourceWalletId = value;
  }

  get destinationWalletId(): string {
    return this._destinationWalletId;
  }

  set destinationWalletId(value: string) {
    this._destinationWalletId = value;
  }

  get amount(): string {
    return this._amount;
  }

  set amount(value: string) {
    this._amount = value;
  }

  get destinationAccount(): AccountDto {
    return this._destinationAccount;
  }

  set destinationAccount(value: AccountDto) {
    this._destinationAccount = value;
  }
}
