import { Component } from '@angular/core';
import {AuthService} from "../../_services/auth.service";
import {GroupService} from "../../_services/group.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-create-group',
  templateUrl: './create-group.component.html',
  styleUrls: ['./create-group.component.css']
})
export class CreateGroupComponent {
  form: any = {
    title: null,
    description: null,
  };
  isSuccessful = false;
  isGroupCreationFailed = false;
  errorMessage = '';

  constructor(private authService: AuthService,
              private groupService: GroupService,
              private router : Router) { }

  ngOnInit(): void {
  }

  onSubmit(): void {
    const { title, description } = this.form;
    this.groupService.createGroup(title, description).subscribe({
      next: data => {
        console.log(data);
        this.isSuccessful = true;
        this.isGroupCreationFailed = false;
      },
      error: err => {
        this.errorMessage = err.error.message;
        this.isGroupCreationFailed = true;
      }
    });

  }
}
