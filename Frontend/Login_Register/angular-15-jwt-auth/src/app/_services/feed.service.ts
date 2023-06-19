import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs';
import {Ride} from "../ride/ride.interface";

const API_URL = 'http://localhost:9091/message/';
const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};
@Injectable({
  providedIn: 'root',
})
export class FeedService {
  constructor(private http: HttpClient) {}

  getGroupMessages(groupId: number | undefined) : Observable<any> {
    return this.http.get(
      API_URL + 'getAll/' + groupId,
    );
  }

  createMessage(text: string, groupId: number | undefined) : Observable<any> {
    return this.http.post(
      API_URL + 'create',
      {
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
