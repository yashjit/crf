export interface IZone {
  id?: string;
  zoneName?: string;
}

export class Zone implements IZone {
  constructor(public id?: string, public zoneName?: string) {}
}
