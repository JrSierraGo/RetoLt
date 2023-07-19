import { Location } from "./Location";

export interface Sofkiano {
    id: string;
    documentTypeId: string;
    documentNumber: string;
    name: string;
    lastName: string;
    entryDate: number;
    customerId: string;
    status: string;
    skills: string[]
    location: Location
  }