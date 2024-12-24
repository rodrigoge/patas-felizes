import { AccountInterface } from "./AccountInterface";

export interface PetInterface {
  name: string;
  address: string;
  type: string;
  age: number;
  breed: string;
  avatar: string;
  giver: AccountInterface;
  receiver: AccountInterface;
}
