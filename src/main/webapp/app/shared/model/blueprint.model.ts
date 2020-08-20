export interface IBlueprint {
  id?: string;
  title?: string;
  type?: string;
  description?: string;
  template?: string;
  custom?: string;
  workloadTitle?: string;
  workloadId?: string;
}

export class Blueprint implements IBlueprint {
  constructor(
    public id?: string,
    public title?: string,
    public type?: string,
    public description?: string,
    public template?: string,
    public custom?: string,
    public workloadTitle?: string,
    public workloadId?: string
  ) {}
}
