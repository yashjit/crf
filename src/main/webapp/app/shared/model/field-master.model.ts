export interface IFieldMaster {
  id?: string;
  name?: string;
  value?: string;
  type?: string;
  description?: string;
  isRequired?: boolean;
  custom?: string;
}

export class FieldMaster implements IFieldMaster {
  constructor(
    public id?: string,
    public name?: string,
    public value?: string,
    public type?: string,
    public description?: string,
    public isRequired?: boolean,
    public custom?: string
  ) {
    this.isRequired = this.isRequired || false;
  }
}
