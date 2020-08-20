import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CrfSharedModule } from 'app/shared/shared.module';
import { ApplicationComponent } from './application.component';
import { ApplicationDetailComponent } from './application-detail.component';
import { ApplicationUpdateComponent } from './application-update.component';
import { ApplicationDeleteDialogComponent } from './application-delete-dialog.component';
import { applicationRoute } from './application.route';

@NgModule({
  imports: [CrfSharedModule, RouterModule.forChild(applicationRoute)],
  declarations: [ApplicationComponent, ApplicationDetailComponent, ApplicationUpdateComponent, ApplicationDeleteDialogComponent],
  entryComponents: [ApplicationDeleteDialogComponent],
})
export class CrfApplicationModule {}
