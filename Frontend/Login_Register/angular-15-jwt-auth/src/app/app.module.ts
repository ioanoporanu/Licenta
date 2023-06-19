import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';

import { AppComponent } from './app.component';
import { LoginComponent } from './user/login/login.component';
import { RegisterComponent } from './user/register/register.component';
import { HomeComponent } from './home/home.component';
import { ProfileComponent } from './profile/profile.component';
import { BoardAdminComponent } from './user/board-admin/board-admin.component';
import { BoardModeratorComponent } from './user/board-moderator/board-moderator.component';
import { BoardUserComponent } from './user/board-user/board-user.component';
import { TranslateModule } from '@ngx-translate/core';
import { StreamChatModule, StreamAutocompleteTextareaModule } from 'stream-chat-angular';
import { httpInterceptorProviders } from './_helpers/http.interceptor';
import { FeedElementComponent } from './group/feed-element/feed-element.component';
import { GroupComponent } from './group/group.component';
import { MatTableModule } from '@angular/material/table';
import { GroupDisplayComponent } from './group/group-display/group-display.component';
import { CreateGroupComponent } from './group/create-group/create-group.component';
import { UserComponent } from './user/user.component';
import { UserDisplayComponent } from './user/user-display/user-display.component';
import { FeedModalComponent } from './group/feed-element/feed-modal/feed-modal.component'
import {MatDialogModule, MatDialogRef} from "@angular/material/dialog";
import { RideComponent } from './ride/ride.component';
import { GoogleMapsModule } from '@angular/google-maps';
import { MapSearchBoxComponent } from './map/map-search-box/map-search-box.component';
import { MapComponent } from './map/map.component'
import {MatIconModule} from "@angular/material/icon";
import {MatFormFieldModule} from "@angular/material/form-field";
import {
  MAT_DATETIME_FORMATS,
  MatDatetimepickerModule
} from "@mat-datetimepicker/core";
import { MatMomentDatetimeModule } from "@mat-datetimepicker/moment";
import { MatDatepickerModule } from "@angular/material/datepicker";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { DateTimePickComponent } from './ride/date-time-pick/date-time-pick.component';
import { SearchRideComponent } from './ride/search-ride/search-ride.component';
import { ReactiveFormsModule} from "@angular/forms";
import { CreateRideComponent } from './ride/create-ride/create-ride.component';
import { GroupUserAddModalComponent } from './group/group-user-add-modal/group-user-add-modal.component';
import {DatePipe} from "@angular/common";
import { RidesDisplayComponent } from './ride/rides-display/rides-display.component';
import { MapRenderDirectionsComponent } from './map/map-render-directions/map-render-directions.component';
import { GroupDisplayModalComponent } from './group/group-display-modal/group-display-modal.component';

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
    RideComponent,
    MapSearchBoxComponent,
    MapComponent,
    DateTimePickComponent,
    SearchRideComponent,
    CreateRideComponent,
    GroupUserAddModalComponent,
    RidesDisplayComponent,
    MapRenderDirectionsComponent,
    GroupDisplayModalComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatTableModule,
    MatDialogModule,
    GoogleMapsModule,
    MatIconModule,
    MatFormFieldModule,
    MatDatepickerModule,
    MatMomentDatetimeModule,
    MatDatetimepickerModule,
    BrowserAnimationsModule,
    TranslateModule.forRoot(),
    StreamAutocompleteTextareaModule,
    StreamChatModule,
  ],
  providers: [httpInterceptorProviders, MatDatepickerModule, DatePipe],
  bootstrap: [AppComponent],
})
export class AppModule { }
