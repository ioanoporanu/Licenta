import {Component, Inject} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {DatePipe} from "@angular/common";
import {User} from "stream-chat";
import {UserService} from "../_services/user.service";
import {Reply} from "./reply.interface";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {GroupDisplayComponent} from "../group/group-display/group-display.component";
import {ReplyService} from "../_services/reply.service";
import {FeedElement} from "../group/feed-element/feed-element-interface";

@Component({
  selector: 'app-reply',
  templateUrl: './reply.component.html',
  styleUrls: ['./reply.component.css']
})
export class ReplyComponent {

  text?: string;
  currentUser!: User;
  replies!: Reply[];
  message!: FeedElement;

  constructor(
    private route: ActivatedRoute,
    public datePipe: DatePipe,
    private userService: UserService,
    private dialogRef: MatDialogRef<GroupDisplayComponent>,
    private replyService: ReplyService,
    @Inject(MAT_DIALOG_DATA) private data: FeedElement
  ) {
    console.log(data);
    this.replies = Object.values(data.replies) as Reply[];
    this.message = data as FeedElement;
  }

  ngOnInit(){
    this.userService.getCurrentUser().subscribe({
      next: data => {
        this.currentUser = JSON.parse(data) as User;
      }
    });
  }

  sendMessage() {
    this.text = (<HTMLInputElement>document.getElementById('myText')).value;
    let varX = {
      "text":this.text,
      "messageId": this.data.messageDeleteId,
      "ownerUsername":this.currentUser.username,
    };
    this.replies.push(varX as Reply);
    this.replyService.createReply(this.text, this.data.messageDeleteId, new Date()).subscribe({
      next: data => {
      }
    });

    (<HTMLInputElement>document.getElementById('myText')).value = "";
    this.updateScroll();
  }
  updateScroll(){
    let element = document.getElementById("chatBox");
    if(element)
      element.scrollTop = element.scrollHeight;
  }
}
