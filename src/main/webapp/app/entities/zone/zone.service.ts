import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IZone } from 'app/shared/model/zone.model';

type EntityResponseType = HttpResponse<IZone>;
type EntityArrayResponseType = HttpResponse<IZone[]>;

@Injectable({ providedIn: 'root' })
export class ZoneService {
  public resourceUrl = SERVER_API_URL + 'api/zones';

  constructor(protected http: HttpClient) {}

  create(zone: IZone): Observable<EntityResponseType> {
    return this.http.post<IZone>(this.resourceUrl, zone, { observe: 'response' });
  }

  update(zone: IZone): Observable<EntityResponseType> {
    return this.http.put<IZone>(this.resourceUrl, zone, { observe: 'response' });
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http.get<IZone>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IZone[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
