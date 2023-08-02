import {BaseDto} from "./base-dto";

export interface AccountDto extends BaseDto {
  userId: string;
  accountName: string;
  walletId: string;
  balance: string;
}
