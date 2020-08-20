import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'project',
        loadChildren: () => import('./project/project.module').then(m => m.CrfProjectModule),
      },
      {
        path: 'organization',
        loadChildren: () => import('./organization/organization.module').then(m => m.CrfOrganizationModule),
      },
      {
        path: 'team',
        loadChildren: () => import('./team/team.module').then(m => m.CrfTeamModule),
      },
      {
        path: 'country',
        loadChildren: () => import('./country/country.module').then(m => m.CrfCountryModule),
      },
      {
        path: 'location',
        loadChildren: () => import('./location/location.module').then(m => m.CrfLocationModule),
      },
      {
        path: 'region',
        loadChildren: () => import('./region/region.module').then(m => m.CrfRegionModule),
      },
      {
        path: 'zone',
        loadChildren: () => import('./zone/zone.module').then(m => m.CrfZoneModule),
      },
      {
        path: 'field-master',
        loadChildren: () => import('./field-master/field-master.module').then(m => m.CrfFieldMasterModule),
      },
      {
        path: 'config-master',
        loadChildren: () => import('./config-master/config-master.module').then(m => m.CrfConfigMasterModule),
      },
      {
        path: 'wave',
        loadChildren: () => import('./wave/wave.module').then(m => m.CrfWaveModule),
      },
      {
        path: 'application',
        loadChildren: () => import('./application/application.module').then(m => m.CrfApplicationModule),
      },
      {
        path: 'workload',
        loadChildren: () => import('./workload/workload.module').then(m => m.CrfWorkloadModule),
      },
      {
        path: 'blueprint',
        loadChildren: () => import('./blueprint/blueprint.module').then(m => m.CrfBlueprintModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class CrfEntityModule {}
