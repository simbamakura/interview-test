import {AccountDto} from "./account-dto";

export interface AccountTransactionDto {
  amount: string;
  balanceBeforeTransaction: string;
  balanceAfterTransaction: string;
  charges: string;
  transactionReference: string;
  transactionType: string;
  sourceAccount: AccountDto;
  destinationAccount: AccountDto;
}
