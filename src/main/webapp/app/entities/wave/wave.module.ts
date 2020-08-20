import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CrfSharedModule } from 'app/shared/shared.module';
import { WaveComponent } from './wave.component';
import { WaveDetailComponent } from './wave-detail.component';
import { WaveUpdateComponent } from './wave-update.component';
import { WaveDeleteDialogComponent } from './wave-delete-dialog.component';
import { waveRoute } from './wave.route';

@NgModule({
  imports: [CrfSharedModule, RouterModule.forChild(waveRoute)],
  declarations: [WaveComponent, WaveDetailComponent, WaveUpdateComponent, WaveDeleteDialogComponent],
  entryComponents: [WaveDeleteDialogComponent],
})
export class CrfWaveModule {}
