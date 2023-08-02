import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterModule, Routes} from "@angular/router";
import {AccountBalancesComponent} from "./balances/account-balances/account-balances.component";

const routes: Routes = [
  {
    path: '',
    component: AccountBalancesComponent,
    data: {
      title: `Balances`
    }
  }
];


@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ViewsroutingModule { }
