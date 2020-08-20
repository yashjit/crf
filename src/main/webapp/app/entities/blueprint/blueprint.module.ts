import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CrfSharedModule } from 'app/shared/shared.module';
import { BlueprintComponent } from './blueprint.component';
import { BlueprintDetailComponent } from './blueprint-detail.component';
import { BlueprintUpdateComponent } from './blueprint-update.component';
import { BlueprintDeleteDialogComponent } from './blueprint-delete-dialog.component';
import { blueprintRoute } from './blueprint.route';

@NgModule({
  imports: [CrfSharedModule, RouterModule.forChild(blueprintRoute)],
  declarations: [BlueprintComponent, BlueprintDetailComponent, BlueprintUpdateComponent, BlueprintDeleteDialogComponent],
  entryComponents: [BlueprintDeleteDialogComponent],
})
export class CrfBlueprintModule {}
