import {Component, Input} from '@angular/core';
import {FeedElement} from "./feed-element-interface";


@Component({
  selector: 'app-feed-element',
  templateUrl: './feed-element.component.html',
  styleUrls: ['./feed-element.component.css'],
})
export class FeedElementComponent{
  feedElementDetails?: FeedElement;

  @Input()
  feed!: FeedElement;

  @Input()
  groupName!: string;

  ngOnInit(): void {

  }

}

