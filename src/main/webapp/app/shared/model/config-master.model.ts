export interface IConfigMaster {
  id?: string;
  name?: string;
  value?: string;
  type?: string;
  description?: string;
  isRequired?: boolean;
  custom?: string;
  organizationOrgName?: string;
  organizationId?: string;
}

export class ConfigMaster implements IConfigMaster {
  constructor(
    public id?: string,
    public name?: string,
    public value?: string,
    public type?: string,
    public description?: string,
    public isRequired?: boolean,
    public custom?: string,
    public organizationOrgName?: string,
    public organizationId?: string
  ) {
    this.isRequired = this.isRequired || false;
  }
}
