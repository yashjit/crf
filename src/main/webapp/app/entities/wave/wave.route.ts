import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IWave, Wave } from 'app/shared/model/wave.model';
import { WaveService } from './wave.service';
import { WaveComponent } from './wave.component';
import { WaveDetailComponent } from './wave-detail.component';
import { WaveUpdateComponent } from './wave-update.component';

@Injectable({ providedIn: 'root' })
export class WaveResolve implements Resolve<IWave> {
  constructor(private service: WaveService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IWave> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((wave: HttpResponse<Wave>) => {
          if (wave.body) {
            return of(wave.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Wave());
  }
}

export const waveRoute: Routes = [
  {
    path: '',
    component: WaveComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'crfApp.wave.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: WaveDetailComponent,
    resolve: {
      wave: WaveResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'crfApp.wave.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: WaveUpdateComponent,
    resolve: {
      wave: WaveResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'crfApp.wave.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: WaveUpdateComponent,
    resolve: {
      wave: WaveResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'crfApp.wave.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
