import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CrfSharedModule } from 'app/shared/shared.module';
import { ZoneComponent } from './zone.component';
import { ZoneDetailComponent } from './zone-detail.component';
import { ZoneUpdateComponent } from './zone-update.component';
import { ZoneDeleteDialogComponent } from './zone-delete-dialog.component';
import { zoneRoute } from './zone.route';

@NgModule({
  imports: [CrfSharedModule, RouterModule.forChild(zoneRoute)],
  declarations: [ZoneComponent, ZoneDetailComponent, ZoneUpdateComponent, ZoneDeleteDialogComponent],
  entryComponents: [ZoneDeleteDialogComponent],
})
export class CrfZoneModule {}
