import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IFieldMaster } from 'app/shared/model/field-master.model';

type EntityResponseType = HttpResponse<IFieldMaster>;
type EntityArrayResponseType = HttpResponse<IFieldMaster[]>;

@Injectable({ providedIn: 'root' })
export class FieldMasterService {
  public resourceUrl = SERVER_API_URL + 'api/field-masters';

  constructor(protected http: HttpClient) {}

  create(fieldMaster: IFieldMaster): Observable<EntityResponseType> {
    return this.http.post<IFieldMaster>(this.resourceUrl, fieldMaster, { observe: 'response' });
  }

  update(fieldMaster: IFieldMaster): Observable<EntityResponseType> {
    return this.http.put<IFieldMaster>(this.resourceUrl, fieldMaster, { observe: 'response' });
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http.get<IFieldMaster>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IFieldMaster[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
