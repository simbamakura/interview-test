import {HttpHeaders} from "@angular/common/http";

export class Util {
  public static getHeaders(): HttpHeaders {
    return new HttpHeaders(
      {

        'Content-Type': 'application/json',
        'Accept': 'application/json',
        'Source-Type': 'WEB',
        'Language': 'en',
        'Access-Control-Allow-Origin': '*'
      });
  }
}
