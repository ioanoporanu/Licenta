export interface User{

  username: string;

  email: string;

  password: string;

  roles: Set<string>;

  groupsId: Set<number>;

  userId: number;

  kmCoins: number;
}
