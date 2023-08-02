import {Injectable} from "@angular/core";
import {BehaviorSubject, catchError, Observable, of, throwError} from "rxjs";
import {concatMap, finalize, tap} from "rxjs/operators";
import {MessagesService} from "../messages/messages.service";

@Injectable()
export class LoadingService {
  private _loadingSubject = new BehaviorSubject<boolean>(false)
  loading$: Observable<boolean> = this._loadingSubject.asObservable();

  constructor(private messagesService: MessagesService) {
  }

  showLoaderUntilCompleted<T>(obs$: Observable<T>): Observable<T> {
    return of(null)
      .pipe(
        tap(() => this.loadingOn()),
        concatMap(() => obs$),
        finalize(() => this.loadingOff())
      );
  }

  loadingOn() {
    this._loadingSubject.next(true);
  }

  loadingOff() {
    this._loadingSubject.next(false);
  }
}
