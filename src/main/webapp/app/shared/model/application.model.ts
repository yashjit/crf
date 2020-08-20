import { IWorkload } from 'app/shared/model/workload.model';

export interface IApplication {
  id?: string;
  appName?: string;
  appType?: string;
  custom?: string;
  workloads?: IWorkload[];
  waveWaveName?: string;
  waveId?: string;
}

export class Application implements IApplication {
  constructor(
    public id?: string,
    public appName?: string,
    public appType?: string,
    public custom?: string,
    public workloads?: IWorkload[],
    public waveWaveName?: string,
    public waveId?: string
  ) {}
}
