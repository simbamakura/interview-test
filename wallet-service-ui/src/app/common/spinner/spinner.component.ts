import {Component} from '@angular/core';
import {LoadingService} from "../service/loading.service";

@Component({
  selector: 'loading',
  templateUrl: './spinner.component.html',
  styleUrls: ['./spinner.component.css']
})
export class SpinnerComponent {
  constructor(public loadingService: LoadingService) {

  }
}
