import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IWorkload, Workload } from 'app/shared/model/workload.model';
import { WorkloadService } from './workload.service';
import { WorkloadComponent } from './workload.component';
import { WorkloadDetailComponent } from './workload-detail.component';
import { WorkloadUpdateComponent } from './workload-update.component';

@Injectable({ providedIn: 'root' })
export class WorkloadResolve implements Resolve<IWorkload> {
  constructor(private service: WorkloadService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IWorkload> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((workload: HttpResponse<Workload>) => {
          if (workload.body) {
            return of(workload.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Workload());
  }
}

export const workloadRoute: Routes = [
  {
    path: '',
    component: WorkloadComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'crfApp.workload.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: WorkloadDetailComponent,
    resolve: {
      workload: WorkloadResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'crfApp.workload.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: WorkloadUpdateComponent,
    resolve: {
      workload: WorkloadResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'crfApp.workload.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: WorkloadUpdateComponent,
    resolve: {
      workload: WorkloadResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'crfApp.workload.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
