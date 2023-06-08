import {ChangeDetectionStrategy, Component, OnInit} from '@angular/core';
import { UserService } from '../../_services/user.service';
import {Group} from "../../group/group-interfaces";

@Component({
  selector: 'app-board-user',
  templateUrl: './board-user.component.html',
  styleUrls: ['./board-user.component.css']
})
export class BoardUserComponent{
  content?: string;


}
