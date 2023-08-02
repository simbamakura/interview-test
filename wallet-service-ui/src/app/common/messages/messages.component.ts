import {Component, OnInit} from '@angular/core';
import {Observable} from 'rxjs';
import {tap} from 'rxjs/operators';
import {MessagesService} from "./messages.service";
import {freeSet} from "@coreui/icons";

@Component({
  selector: 'messages',
  templateUrl: './messages.component.html',
  styleUrls: ['./messages.component.css']
})
export class MessagesComponent implements OnInit {

  showMessages= false;

  errors$: Observable<string[]>;
  icons = freeSet ;


  constructor(public messageService: MessagesService) {

  }

  ngOnInit() {
    this.errors$ = this.messageService.errors$.pipe(
      tap((messages) => {
        this.showMessages = messages.length > 0;
      })
    );

  }


  onClose() {
    this.showMessages= false;
  }

}
