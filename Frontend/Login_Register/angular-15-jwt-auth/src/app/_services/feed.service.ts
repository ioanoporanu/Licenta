import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs';
import {Ride} from "../ride/ride.interface";
import {FeedElement} from "../group/feed-element/feed-element-interface";

const API_URL = 'http://localhost:9091/message/';
const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};
@Injectable({
  providedIn: 'root',
})
export class FeedService {
  constructor(private http: HttpClient) {}

  getGroupMessages(groupId: number | undefined) : Observable<Set<FeedElement>> {
    return this.http.get<Set<FeedElement>>(
      API_URL + 'getAll/' + groupId,
    );
  }

  createMessage(text: string, groupId: number | undefined, date: Date) : Observable<any> {
    console.log(date)
    return this.http.post(
      API_URL + 'create',
      {
        date,
        text,
        groupId,
      },
      httpOptions
    );
  }

  deleteMessage(id:number) : Observable<any> {
    return this.http.delete(
      API_URL + 'delete/' + id,
    );
  }
}
