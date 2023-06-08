import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { ProfileComponent } from './profile/profile.component';
import { BoardUserComponent } from './user/board-user/board-user.component';
import { BoardModeratorComponent } from './user/board-moderator/board-moderator.component';
import { BoardAdminComponent } from './user/board-admin/board-admin.component';
import {GroupDisplayComponent} from "./group/group-display/group-display.component";
import {CreateGroupComponent} from "./group/create-group/create-group.component";
import {GroupComponent} from "./group/group.component";
import {Group} from "./group/group-interfaces";

const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'profile', component: ProfileComponent },
  { path: 'user', component: BoardUserComponent },
  { path: 'mod', component: BoardModeratorComponent },
  { path: 'admin', component: BoardAdminComponent },
  { path: 'groups', component: BoardUserComponent },
  { path: 'create-group', component: CreateGroupComponent },
  { path: 'groups/:group', component: GroupComponent},
  { path: '', redirectTo: 'home', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
