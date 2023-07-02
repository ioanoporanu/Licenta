import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs';
import {Ride} from "../ride/ride.interface";

const API_URL = 'http://localhost:9091/reply/';
const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};
@Injectable({
  providedIn: 'root',
})
export class ReplyService {
  constructor(private http: HttpClient) {}

  createReply(text: string, messageId: number, date: Date) : Observable<any> {
    return this.http.post(
      API_URL + 'create',
      {
        date,
        text,
        messageId,
      },
      httpOptions
    );
  }

}
