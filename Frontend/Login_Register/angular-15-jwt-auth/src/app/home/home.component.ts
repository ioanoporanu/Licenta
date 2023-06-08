import { Component, OnInit } from '@angular/core';
import { UserService } from '../_services/user.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
   content?: string;

   screenVisible: Map<string, boolean> = new Map<string, boolean>().set("Drive", true).set("Travel", false);
   screenSelected: Map<string, boolean> = new Map<string, boolean>().set("Drive", true).set("Travel", false);

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.userService.getPublicContent().subscribe({
      next: data => {
        this.content = data;
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

  onClickDrive(){
    this.screenVisible.set("Drive", true);
    this.screenVisible.set("Travel", false);

    this.screenSelected.set("Drive", true);
    this.screenSelected.set("Travel", false);
  }

  onClickTravel(){
    this.screenVisible.set("Drive", false);
    this.screenVisible.set("Travel", true);

    this.screenSelected.set("Drive", false);
    this.screenSelected.set("Travel", true);
  }
}
