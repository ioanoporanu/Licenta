import {Reply} from "../../reply/reply.interface";

export interface FeedElement{
  groupId: number;
  messageDeleteId: number;
  text: string;
  ownerName: string;
  replies: Set<Reply>;
  date: Date;
}
