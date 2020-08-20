import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CrfSharedModule } from 'app/shared/shared.module';
import { FieldMasterComponent } from './field-master.component';
import { FieldMasterDetailComponent } from './field-master-detail.component';
import { FieldMasterUpdateComponent } from './field-master-update.component';
import { FieldMasterDeleteDialogComponent } from './field-master-delete-dialog.component';
import { fieldMasterRoute } from './field-master.route';

@NgModule({
  imports: [CrfSharedModule, RouterModule.forChild(fieldMasterRoute)],
  declarations: [FieldMasterComponent, FieldMasterDetailComponent, FieldMasterUpdateComponent, FieldMasterDeleteDialogComponent],
  entryComponents: [FieldMasterDeleteDialogComponent],
})
export class CrfFieldMasterModule {}
