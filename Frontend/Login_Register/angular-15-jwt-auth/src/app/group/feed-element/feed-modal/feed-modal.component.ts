import {Component, Inject} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";

@Component({
  selector: 'app-feed-modal',
  templateUrl: './feed-modal.component.html',
  styleUrls: ['./feed-modal.component.css']
})
export class FeedModalComponent {
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
