import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IWorkload } from 'app/shared/model/workload.model';

type EntityResponseType = HttpResponse<IWorkload>;
type EntityArrayResponseType = HttpResponse<IWorkload[]>;

@Injectable({ providedIn: 'root' })
export class WorkloadService {
  public resourceUrl = SERVER_API_URL + 'api/workloads';

  constructor(protected http: HttpClient) {}

  create(workload: IWorkload): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(workload);
    return this.http
      .post<IWorkload>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(workload: IWorkload): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(workload);
    return this.http
      .put<IWorkload>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<IWorkload>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IWorkload[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(workload: IWorkload): IWorkload {
    const copy: IWorkload = Object.assign({}, workload, {
      createDate: workload.createDate && workload.createDate.isValid() ? workload.createDate.toJSON() : undefined,
      modifyDate: workload.modifyDate && workload.modifyDate.isValid() ? workload.modifyDate.toJSON() : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.createDate = res.body.createDate ? moment(res.body.createDate) : undefined;
      res.body.modifyDate = res.body.modifyDate ? moment(res.body.modifyDate) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((workload: IWorkload) => {
        workload.createDate = workload.createDate ? moment(workload.createDate) : undefined;
        workload.modifyDate = workload.modifyDate ? moment(workload.modifyDate) : undefined;
      });
    }
    return res;
  }
}
