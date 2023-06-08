import { Component } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {FeedService} from "../_services/feed.service";
import {MatDialog} from "@angular/material/dialog";
import {FeedModalComponent} from "./feed-element/feed-modal/feed-modal.component";
import {Group} from "./group-interfaces";
import {FeedElement} from "./feed-element/feed-element-interface";

@Component({
  selector: 'app-group',
  templateUrl: './group.component.html',
  styleUrls: ['./group.component.css']
})
export class GroupComponent {
  group!: Group;

  //users = new Set([...[this.group.usersUsername]].filter(element => !this.group?.ownersUsername?.has(JSON.stringify(element))));

  content?: string;

  feedElements!: Set<FeedElement>;
  constructor(private route: ActivatedRoute,
              private dialog: MatDialog,
              private feedService: FeedService) { }

  ngOnInit() {
    this.route.paramMap.subscribe(params => {
      this.group = history.state.currentGroup;
    });

    this.feedService.getGroupMessages(this.group.groupDeleteId).subscribe({
      next: data => {
        this.feedElements = data as Set<FeedElement>;
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

    console.log(this.group.usersUsername)
  }

  openModal() {
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

}
