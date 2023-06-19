import {Component, EventEmitter, Output} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {FeedService} from "../_services/feed.service";
import {MatDialog} from "@angular/material/dialog";
import {FeedModalComponent} from "./feed-element/feed-modal/feed-modal.component";
import {Group} from "./group-interfaces";
import {FeedElement} from "./feed-element/feed-element-interface";
import {GroupUserAddModalComponent} from "./group-user-add-modal/group-user-add-modal.component";
import {GroupService} from "../_services/group.service";
import {UserService} from "../_services/user.service";
import {Ride} from "../ride/ride.interface";
import {GroupDisplayModalComponent} from "./group-display-modal/group-display-modal.component";
import {User} from "../user/user.interface";
import {RideService} from "../_services/ride.service";
import {DatePipe} from "@angular/common";

@Component({
  selector: 'app-group',
  templateUrl: './group.component.html',
  styleUrls: ['./group.component.css']
})
export class GroupComponent {
  group!: Group;

  currentUser!: User;

  content?: string;

  sharedRides!: Set<Ride>;

  feedElements!: Set<FeedElement>;
  constructor(private route: ActivatedRoute,
              private dialog: MatDialog,
              private feedService: FeedService,
              private groupService: GroupService,
              private userService: UserService,
              private rideService: RideService,
              public datePipe: DatePipe) { }

  ngOnInit() {
    this.route.paramMap.subscribe(params => {
      this.group = history.state.currentGroup;
    });

    this.feedService.getGroupMessages(this.group.groupDeleteId).subscribe({
      next: data => {
        this.feedElements = data as Set<FeedElement>;
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

    this.userService.getCurrentUser().subscribe({
      next: data => {
        this.currentUser = JSON.parse(data) as User;
        this.groupService.getSharedRides(this.group.groupDeleteId).subscribe({
          next: data => {
            this.sharedRides = data as Set<Ride>;

            for(let a of this.sharedRides){
              if(a.customersId === null){
                a.customersId = [];
              }
              a.customersId = Object.values(a.customersId);
            }
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
      },
      error: err => {
      }
    });

  }

  openFeedModal() {
    const dialogRef =  this.dialog.open(FeedModalComponent);
    dialogRef.afterClosed().subscribe((data) => {
      if (data) {
         this.feedService.createMessage(data, this.group.groupDeleteId).subscribe({
           next: data => {
             window.location.reload();
           },
           error: err => {
           }
         });
      } else {
      }
    });
  }

  openUserAddModal() {
    const dialogRef =  this.dialog.open(GroupUserAddModalComponent);
    dialogRef.afterClosed().subscribe((data) => {
      if (data) {
        let groupDTO = JSON.parse(JSON.stringify(this.group))
        groupDTO.userAddUsername = data;
        this.groupService.updateGroup(groupDTO).subscribe({
          next: data => {
            this.groupService.getGroup(this.group.groupDeleteId).subscribe({
              next: data => {
                this.group = JSON.parse(data) as Group;
              },
              error: err => {
              }
            });
          },
          error: err => {
          }
        });
      } else {
      }
    });

  }

  onClickDelete(userDeleteUsername: string){
    let groupDTO = JSON.parse(JSON.stringify(this.group))
    groupDTO.userDeleteUsername = userDeleteUsername;
    this.groupService.updateGroup(groupDTO).subscribe({
      next: data => {
        this.groupService.getGroup(this.group.groupDeleteId).subscribe({
          next: data => {
            this.group = JSON.parse(data) as Group;
          },
          error: err => {
          }
        });
      },
      error: err => {
      }
    });
  }

  onClickBook (ride: Ride) {
    if(!confirm("Are you sure to book this ride"))
      return;

    this.userService.getCurrentUser().subscribe({
      next: data => {
        let user = JSON.parse(data) as User;

        ride.customerAddId = user.userId;
        ride.availableSeats--;
        this.rideService.updateRide(ride).subscribe({
          next: data => {
            window.location.reload();
          },
          error: err => {

          }
        });

        ride.customerDeleteId = 0;
      },
      error: err => {

      }
    });

  }

  sharedRide(ride: Ride){
    const dialogRef = this.dialog.open(GroupDisplayModalComponent,{
      width: '700px',
      height:'700px',
      data: ride.rideId
    });
  }

}
