export interface Group {
  usersUsername?: Set<string>;

  ownersUsername?: Set<string>;

  name?: string;

  groupDeleteId?: number;

  userDeleteUsername?: string;

  ownerDeleteUsername?: string;

  rideShareId?: number;

  userAddUsername?: string;

  ownerAddUsername?: string;

  description?: string;
}


