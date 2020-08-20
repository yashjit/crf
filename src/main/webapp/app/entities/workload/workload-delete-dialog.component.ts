import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IWorkload } from 'app/shared/model/workload.model';
import { WorkloadService } from './workload.service';

@Component({
  templateUrl: './workload-delete-dialog.component.html',
})
export class WorkloadDeleteDialogComponent {
  workload?: IWorkload;

  constructor(protected workloadService: WorkloadService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.workloadService.delete(id).subscribe(() => {
      this.eventManager.broadcast('workloadListModification');
      this.activeModal.close();
    });
  }
}
