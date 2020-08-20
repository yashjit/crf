import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IBlueprint } from 'app/shared/model/blueprint.model';

type EntityResponseType = HttpResponse<IBlueprint>;
type EntityArrayResponseType = HttpResponse<IBlueprint[]>;

@Injectable({ providedIn: 'root' })
export class BlueprintService {
  public resourceUrl = SERVER_API_URL + 'api/blueprints';

  constructor(protected http: HttpClient) {}

  create(blueprint: IBlueprint): Observable<EntityResponseType> {
    return this.http.post<IBlueprint>(this.resourceUrl, blueprint, { observe: 'response' });
  }

  update(blueprint: IBlueprint): Observable<EntityResponseType> {
    return this.http.put<IBlueprint>(this.resourceUrl, blueprint, { observe: 'response' });
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http.get<IBlueprint>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IBlueprint[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
