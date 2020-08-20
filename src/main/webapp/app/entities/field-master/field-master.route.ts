import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IFieldMaster, FieldMaster } from 'app/shared/model/field-master.model';
import { FieldMasterService } from './field-master.service';
import { FieldMasterComponent } from './field-master.component';
import { FieldMasterDetailComponent } from './field-master-detail.component';
import { FieldMasterUpdateComponent } from './field-master-update.component';

@Injectable({ providedIn: 'root' })
export class FieldMasterResolve implements Resolve<IFieldMaster> {
  constructor(private service: FieldMasterService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IFieldMaster> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((fieldMaster: HttpResponse<FieldMaster>) => {
          if (fieldMaster.body) {
            return of(fieldMaster.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new FieldMaster());
  }
}

export const fieldMasterRoute: Routes = [
  {
    path: '',
    component: FieldMasterComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'crfApp.fieldMaster.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: FieldMasterDetailComponent,
    resolve: {
      fieldMaster: FieldMasterResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'crfApp.fieldMaster.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: FieldMasterUpdateComponent,
    resolve: {
      fieldMaster: FieldMasterResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'crfApp.fieldMaster.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: FieldMasterUpdateComponent,
    resolve: {
      fieldMaster: FieldMasterResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'crfApp.fieldMaster.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
