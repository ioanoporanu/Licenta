import {Component, Input} from '@angular/core';
import {FeedElement} from "./feed-element-interface";
import {FeedService} from "../../_services/feed.service";
import {Group} from "../group-interfaces";


@Component({
  selector: 'app-feed-element',
  templateUrl: './feed-element.component.html',
  styleUrls: ['./feed-element.component.css'],
})
export class FeedElementComponent{
  constructor(private feedService: FeedService) {
  }

  feedElementDetails?: FeedElement;

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

}

