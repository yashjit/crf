import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITeam } from 'app/shared/model/team.model';

type EntityResponseType = HttpResponse<ITeam>;
type EntityArrayResponseType = HttpResponse<ITeam[]>;

@Injectable({ providedIn: 'root' })
export class TeamService {
  public resourceUrl = SERVER_API_URL + 'api/teams';

  constructor(protected http: HttpClient) {}

  create(team: ITeam): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(team);
    return this.http
      .post<ITeam>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(team: ITeam): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(team);
    return this.http
      .put<ITeam>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<ITeam>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ITeam[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(team: ITeam): ITeam {
    const copy: ITeam = Object.assign({}, team, {
      createDate: team.createDate && team.createDate.isValid() ? team.createDate.toJSON() : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.createDate = res.body.createDate ? moment(res.body.createDate) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((team: ITeam) => {
        team.createDate = team.createDate ? moment(team.createDate) : undefined;
      });
    }
    return res;
  }
}
