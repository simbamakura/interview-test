export interface TransferFundsRequestDto {
  sourceWalletId: string | undefined;
  destinationWalletId: string | undefined;
  amount: string| undefined;
}
