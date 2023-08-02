export interface SofkianoForm {
    id: string;
    documentTypeId: string;
    documentNumber: string;
    name: string;
    lastName: string;
    entryDate: Date;
    customerId: string;
    status?: string;
    cityId: string
    address: string;
    neighborhood: string;
    additionalIndications?: string;
    skills?: string[]
  }