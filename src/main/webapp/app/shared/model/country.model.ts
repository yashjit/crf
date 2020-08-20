import { ILocation } from 'app/shared/model/location.model';

export interface ICountry {
  id?: string;
  countryName?: string;
  countryCode?: number;
  locations?: ILocation[];
}

export class Country implements ICountry {
  constructor(public id?: string, public countryName?: string, public countryCode?: number, public locations?: ILocation[]) {}
}
