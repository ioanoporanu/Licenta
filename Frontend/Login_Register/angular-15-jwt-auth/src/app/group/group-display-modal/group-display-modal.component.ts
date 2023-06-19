import {Component, Inject} from '@angular/core';
import {Group} from "../group-interfaces";
import {UserService} from "../../_services/user.service";
import {GroupService} from "../../_services/group.service";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {GroupDisplayComponent} from "../group-display/group-display.component";
import {RideService} from "../../_services/ride.service";

@Component({
  selector: 'app-group-display-modal',
  templateUrl: './group-display-modal.component.html',
  styleUrls: ['./group-display-modal.component.css']
})
export class GroupDisplayModalComponent {
  content?: string;

  groups?: Set<Group>;

  users: Set<number> = new Set<number>();

  constructor(private userService: UserService,
              private groupService: GroupService,
              private dialogRef: MatDialogRef<GroupDisplayComponent>,
              @Inject(MAT_DIALOG_DATA) private data: number) { }

  ngOnInit(): void {
    this.userService.getUserGroups().subscribe({
      next: data => {
        this.groups = JSON.parse(data) as Set<Group>;
        console.log(data);
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

  shareRide(group: Group) {
    console.log("pula");
    group.rideShareId = this.data;
    this.groupService.shareRide(group).subscribe({
      next: data => {
        this.dialogRef.close();
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

  onNoClick(): void {
    this.dialogRef.close();
  }
}
