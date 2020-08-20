import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CrfSharedModule } from 'app/shared/shared.module';
import { WorkloadComponent } from './workload.component';
import { WorkloadDetailComponent } from './workload-detail.component';
import { WorkloadUpdateComponent } from './workload-update.component';
import { WorkloadDeleteDialogComponent } from './workload-delete-dialog.component';
import { workloadRoute } from './workload.route';

@NgModule({
  imports: [CrfSharedModule, RouterModule.forChild(workloadRoute)],
  declarations: [WorkloadComponent, WorkloadDetailComponent, WorkloadUpdateComponent, WorkloadDeleteDialogComponent],
  entryComponents: [WorkloadDeleteDialogComponent],
})
export class CrfWorkloadModule {}
