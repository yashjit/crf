import { Moment } from 'moment';

export interface ITeam {
  id?: string;
  teamName?: string;
  createDate?: Moment;
  organizationOrgName?: string;
  organizationId?: string;
}

export class Team implements ITeam {
  constructor(
    public id?: string,
    public teamName?: string,
    public createDate?: Moment,
    public organizationOrgName?: string,
    public organizationId?: string
  ) {}
}
