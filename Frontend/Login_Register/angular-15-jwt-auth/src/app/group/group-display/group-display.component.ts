import {Component, Inject, Optional} from '@angular/core';
import {Group} from "../group-interfaces";
import {UserService} from "../../_services/user.service";
import {GroupService} from "../../_services/group.service";

@Component({
  selector: 'app-group-display',
  templateUrl: './group-display.component.html',
  styleUrls: ['./group-display.component.css']
})
export class GroupDisplayComponent {

  content?: string;

  groups?: Set<Group>;

  users: Set<number> = new Set<number>();

  constructor(private userService: UserService,
              private groupService: GroupService,) { }

  ngOnInit(): void {
    this.userService.getUserGroups().subscribe({
      next: data => {
        this.groups = JSON.parse(data) as Set<Group>;
      },
      error: err => {
        if (err.error) {
          try {
            const res = JSON.parse(err.error);
            this.content = res.message;
          } catch {
            this.content = `Error with status: ${err.status} - ${err.statusText}`;
          }
        } else {
          this.content = `Error with status: ${err.status}`;
        }
      }
    });
  }

  onClickDelete(id: number | undefined): void {
    if(id == undefined)
      return;
    this.groupService.deleteGroup(id).subscribe({
      next: data => {
        window.location.reload();
      },
      error: err => {
        if (err.error) {
          try {
            const res = JSON.parse(err.error);
            this.content = res.message;
          } catch {
            this.content = `Error with status: ${err.status} - ${err.statusText}`;
          }
        } else {
          this.content = `Error with status: ${err.status}`;
        }
      }
    });

  }

}
