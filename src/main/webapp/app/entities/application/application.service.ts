import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IApplication } from 'app/shared/model/application.model';

type EntityResponseType = HttpResponse<IApplication>;
type EntityArrayResponseType = HttpResponse<IApplication[]>;

@Injectable({ providedIn: 'root' })
export class ApplicationService {
  public resourceUrl = SERVER_API_URL + 'api/applications';

  constructor(protected http: HttpClient) {}

  create(application: IApplication): Observable<EntityResponseType> {
    return this.http.post<IApplication>(this.resourceUrl, application, { observe: 'response' });
  }

  update(application: IApplication): Observable<EntityResponseType> {
    return this.http.put<IApplication>(this.resourceUrl, application, { observe: 'response' });
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http.get<IApplication>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IApplication[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
