import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs';
import {Group} from "../group/group-interfaces";
import {Ride} from "../ride/ride.interface";

const API_URL = 'http://localhost:9091/group/';
const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};
@Injectable({
  providedIn: 'root',
})
export class GroupService {
  constructor(private http: HttpClient) {}

  getUserGroups(): Observable<any> {
    return this.http.get(API_URL + 'getAll', { responseType: 'text' });
  }

  createGroup(name: string, description: string) : Observable<any> {
    return this.http.post(
      API_URL + 'create',
      {
        name,
        description,
        ownersUsername: [],
        usersUsername: [],
      },
      httpOptions
    );
  }

  getGroup(id: any): Observable<any> {
    return this.http.get(API_URL + 'getById/' + id, { responseType: 'text' });
  }

  updateGroup(group: Group) : Observable<any> {
    return this.http.put(
      API_URL + 'update/' + group.groupDeleteId,
      JSON.stringify(group),
      httpOptions
    );
  }

  deleteGroup(id: number) : Observable<any> {
    return this.http.delete(
      API_URL + 'delete/' + id,
    );
  }

  getSharedRides(id: number | undefined) : Observable<any> {
    return this.http.get(
      API_URL + 'getSharedRides/' + id,
    );
  }

  shareRide(group: Group) {
    return this.http.put(
      API_URL + 'share',
      group,
      httpOptions
    );
  }
}
