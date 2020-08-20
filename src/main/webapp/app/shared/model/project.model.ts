import { Moment } from 'moment';
import { IWave } from 'app/shared/model/wave.model';

export interface IProject {
  id?: string;
  projectName?: string;
  projectType?: string;
  targetCloud?: string;
  startDate?: Moment;
  endDate?: Moment;
  custom?: string;
  status?: string;
  createdBy?: string;
  createDate?: Moment;
  modifyDate?: Moment;
  waves?: IWave[];
  organizationOrgName?: string;
  organizationId?: string;
}

export class Project implements IProject {
  constructor(
    public id?: string,
    public projectName?: string,
    public projectType?: string,
    public targetCloud?: string,
    public startDate?: Moment,
    public endDate?: Moment,
    public custom?: string,
    public status?: string,
    public createdBy?: string,
    public createDate?: Moment,
    public modifyDate?: Moment,
    public waves?: IWave[],
    public organizationOrgName?: string,
    public organizationId?: string
  ) {}
}
