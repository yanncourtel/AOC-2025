import { Delivery } from './Delivery';

export interface Invoice {
  customer: string;
  deliveries: Delivery[];
}
