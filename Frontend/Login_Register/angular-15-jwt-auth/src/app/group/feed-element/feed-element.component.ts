import {Component, Input} from '@angular/core';
import {FeedElement} from "./feed-element-interface";
import {FeedService} from "../../_services/feed.service";
import {Group} from "../group-interfaces";
import {GroupDisplayModalComponent} from "../group-display-modal/group-display-modal.component";
import {MatDialog} from "@angular/material/dialog";
import {ReplyComponent} from "../../reply/reply.component";
import {DatePipe} from "@angular/common";


@Component({
  selector: 'app-feed-element',
  templateUrl: './feed-element.component.html',
  styleUrls: ['./feed-element.component.css'],
})
export class FeedElementComponent{
  constructor(private feedService: FeedService,
              private dialog: MatDialog,
              public datePipe: DatePipe) {
  }

  @Input()
  feed!: FeedElement;

  @Input()
  groupName?: string;

  ngOnInit(): void {

  }

  onDelete(feedDeleteId: number) {
    this.feedService.deleteMessage(feedDeleteId).subscribe({
      next: data => {
        window.location.reload();
      },
      error: err => {
      }
    });
  }

  onOpenReplies(){
    const dialogRef = this.dialog.open(ReplyComponent,{
      width: '700px',
      height:'700px',
      data: this.feed,
    });
  }

}

