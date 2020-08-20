import { Moment } from 'moment';
import { IApplication } from 'app/shared/model/application.model';

export interface IWave {
  id?: string;
  waveName?: string;
  fileName?: string;
  startDate?: Moment;
  endDate?: Moment;
  status?: string;
  custom?: string;
  createdBy?: string;
  createDate?: Moment;
  modifyDate?: Moment;
  applications?: IApplication[];
  projectProjectName?: string;
  projectId?: string;
}

export class Wave implements IWave {
  constructor(
    public id?: string,
    public waveName?: string,
    public fileName?: string,
    public startDate?: Moment,
    public endDate?: Moment,
    public status?: string,
    public custom?: string,
    public createdBy?: string,
    public createDate?: Moment,
    public modifyDate?: Moment,
    public applications?: IApplication[],
    public projectProjectName?: string,
    public projectId?: string
  ) {}
}
