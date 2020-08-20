import { Moment } from 'moment';
import { IBlueprint } from 'app/shared/model/blueprint.model';
import { IApplication } from 'app/shared/model/application.model';

export interface IWorkload {
  id?: string;
  title?: string;
  type?: string;
  movegroup?: number;
  serverName?: string;
  serverTier?: string;
  os?: string;
  cloudInfo?: string;
  description?: string;
  custom?: string;
  status?: string;
  createDate?: Moment;
  modifyDate?: Moment;
  createdBy?: string;
  blueprints?: IBlueprint[];
  waves?: IApplication[];
}

export class Workload implements IWorkload {
  constructor(
    public id?: string,
    public title?: string,
    public type?: string,
    public movegroup?: number,
    public serverName?: string,
    public serverTier?: string,
    public os?: string,
    public cloudInfo?: string,
    public description?: string,
    public custom?: string,
    public status?: string,
    public createDate?: Moment,
    public modifyDate?: Moment,
    public createdBy?: string,
    public blueprints?: IBlueprint[],
    public waves?: IApplication[]
  ) {}
}
