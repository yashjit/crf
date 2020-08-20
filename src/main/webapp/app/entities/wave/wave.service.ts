import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IWave } from 'app/shared/model/wave.model';

type EntityResponseType = HttpResponse<IWave>;
type EntityArrayResponseType = HttpResponse<IWave[]>;

@Injectable({ providedIn: 'root' })
export class WaveService {
  public resourceUrl = SERVER_API_URL + 'api/waves';

  constructor(protected http: HttpClient) {}

  create(wave: IWave): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(wave);
    return this.http
      .post<IWave>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(wave: IWave): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(wave);
    return this.http
      .put<IWave>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<IWave>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IWave[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(wave: IWave): IWave {
    const copy: IWave = Object.assign({}, wave, {
      startDate: wave.startDate && wave.startDate.isValid() ? wave.startDate.toJSON() : undefined,
      endDate: wave.endDate && wave.endDate.isValid() ? wave.endDate.toJSON() : undefined,
      createDate: wave.createDate && wave.createDate.isValid() ? wave.createDate.toJSON() : undefined,
      modifyDate: wave.modifyDate && wave.modifyDate.isValid() ? wave.modifyDate.toJSON() : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.startDate = res.body.startDate ? moment(res.body.startDate) : undefined;
      res.body.endDate = res.body.endDate ? moment(res.body.endDate) : undefined;
      res.body.createDate = res.body.createDate ? moment(res.body.createDate) : undefined;
      res.body.modifyDate = res.body.modifyDate ? moment(res.body.modifyDate) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((wave: IWave) => {
        wave.startDate = wave.startDate ? moment(wave.startDate) : undefined;
        wave.endDate = wave.endDate ? moment(wave.endDate) : undefined;
        wave.createDate = wave.createDate ? moment(wave.createDate) : undefined;
        wave.modifyDate = wave.modifyDate ? moment(wave.modifyDate) : undefined;
      });
    }
    return res;
  }
}
