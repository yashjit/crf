import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IWave } from 'app/shared/model/wave.model';
import { WaveService } from './wave.service';

@Component({
  templateUrl: './wave-delete-dialog.component.html',
})
export class WaveDeleteDialogComponent {
  wave?: IWave;

  constructor(protected waveService: WaveService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.waveService.delete(id).subscribe(() => {
      this.eventManager.broadcast('waveListModification');
      this.activeModal.close();
    });
  }
}
