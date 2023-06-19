import {Component, Inject} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {FeedModalComponent} from "../feed-element/feed-modal/feed-modal.component";

@Component({
  selector: 'app-group-user-add-modal',
  templateUrl: './group-user-add-modal.component.html',
  styleUrls: ['./group-user-add-modal.component.css']
})
export class GroupUserAddModalComponent {

  displayStyle = "none";
  postText: string="";

  constructor(private dialogRef: MatDialogRef<FeedModalComponent>,
              @Inject(MAT_DIALOG_DATA) private data: any
  ){
  }

  onCancel() {
    this.dialogRef.close();
  }

  onSubmit() {
    this.dialogRef.close(this.postText);
  }

}
