import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { HomeComponent } from './home/home.component';
import { ProfileComponent } from './profile/profile.component';
import { BoardAdminComponent } from './user/board-admin/board-admin.component';
import { BoardModeratorComponent } from './user/board-moderator/board-moderator.component';
import { BoardUserComponent } from './user/board-user/board-user.component';

import { httpInterceptorProviders } from './_helpers/http.interceptor';
import { FeedElementComponent } from './group/feed-element/feed-element.component';
import { GroupComponent } from './group/group.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatTableModule } from '@angular/material/table';
import { GroupDisplayComponent } from './group/group-display/group-display.component';
import { CreateGroupComponent } from './group/create-group/create-group.component';
import { UserComponent } from './user/user.component';
import { UserDisplayComponent } from './user/user-display/user-display.component';
import { FeedModalComponent } from './group/feed-element/feed-modal/feed-modal.component'
import {MatDialogModule} from "@angular/material/dialog";
import { RideComponent } from './ride/ride.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    HomeComponent,
    ProfileComponent,
    BoardAdminComponent,
    BoardModeratorComponent,
    BoardUserComponent,
    GroupComponent,
    GroupDisplayComponent,
    CreateGroupComponent,
    UserComponent,
    UserDisplayComponent,
    FeedModalComponent,
    FeedElementComponent,
    RideComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatTableModule,
    MatDialogModule
  ],
  providers: [httpInterceptorProviders],
  bootstrap: [AppComponent],
  entryComponents: [ FeedModalComponent ]
})
export class AppModule { }
