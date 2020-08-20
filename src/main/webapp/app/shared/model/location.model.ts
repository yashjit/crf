export interface ILocation {
  id?: string;
  title?: string;
  streetAddress?: string;
  postalCode?: string;
  city?: string;
  stateProvince?: string;
  countryCountryName?: string;
  countryId?: string;
}

export class Location implements ILocation {
  constructor(
    public id?: string,
    public title?: string,
    public streetAddress?: string,
    public postalCode?: string,
    public city?: string,
    public stateProvince?: string,
    public countryCountryName?: string,
    public countryId?: string
  ) {}
}
