import { Moment } from 'moment';
import { IProject } from 'app/shared/model/project.model';
import { ITeam } from 'app/shared/model/team.model';
import { IConfigMaster } from 'app/shared/model/config-master.model';

export interface IOrganization {
  id?: string;
  orgName?: string;
  firstName?: string;
  lastName?: string;
  email?: string;
  phoneNumber?: string;
  createDate?: Moment;
  locationTitle?: string;
  locationId?: string;
  projects?: IProject[];
  teams?: ITeam[];
  configMasters?: IConfigMaster[];
}

export class Organization implements IOrganization {
  constructor(
    public id?: string,
    public orgName?: string,
    public firstName?: string,
    public lastName?: string,
    public email?: string,
    public phoneNumber?: string,
    public createDate?: Moment,
    public locationTitle?: string,
    public locationId?: string,
    public projects?: IProject[],
    public teams?: ITeam[],
    public configMasters?: IConfigMaster[]
  ) {}
}
