import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IBlueprint, Blueprint } from 'app/shared/model/blueprint.model';
import { BlueprintService } from './blueprint.service';
import { BlueprintComponent } from './blueprint.component';
import { BlueprintDetailComponent } from './blueprint-detail.component';
import { BlueprintUpdateComponent } from './blueprint-update.component';

@Injectable({ providedIn: 'root' })
export class BlueprintResolve implements Resolve<IBlueprint> {
  constructor(private service: BlueprintService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IBlueprint> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((blueprint: HttpResponse<Blueprint>) => {
          if (blueprint.body) {
            return of(blueprint.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Blueprint());
  }
}

export const blueprintRoute: Routes = [
  {
    path: '',
    component: BlueprintComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'crfApp.blueprint.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: BlueprintDetailComponent,
    resolve: {
      blueprint: BlueprintResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'crfApp.blueprint.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: BlueprintUpdateComponent,
    resolve: {
      blueprint: BlueprintResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'crfApp.blueprint.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: BlueprintUpdateComponent,
    resolve: {
      blueprint: BlueprintResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'crfApp.blueprint.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
