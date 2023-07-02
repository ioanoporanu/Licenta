import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { RegisterComponent } from './user/register/register.component';
import { LoginComponent } from './user/login/login.component';
import { HomeComponent } from './home/home.component';
import { ProfileComponent } from './profile/profile.component';
import { BoardUserComponent } from './user/board-user/board-user.component';
import { BoardModeratorComponent } from './user/board-moderator/board-moderator.component';
import { BoardAdminComponent } from './user/board-admin/board-admin.component';
import {CreateGroupComponent} from "./group/create-group/create-group.component";
import {GroupComponent} from "./group/group.component";
import {MapComponent} from "./map/map.component";
import {CreateRideComponent} from "./ride/create-ride/create-ride.component";
import {ReplyComponent} from "./reply/reply.component";

const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'profile', component: ProfileComponent },
  { path: 'user-home', component: BoardUserComponent },
  { path: 'mod', component: BoardModeratorComponent },
  { path: 'admin', component: BoardAdminComponent },
  { path: 'groups', component: BoardUserComponent },
  { path: 'create-group', component: CreateGroupComponent },
  { path: 'groups/:group', component: GroupComponent},
  { path: 'map', component: MapComponent},
  { path: 'replies', component: ReplyComponent},

  { path: 'ride/create-ride', component: CreateRideComponent},
  { path: '', redirectTo: 'home', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {onSameUrlNavigation: 'reload'})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
