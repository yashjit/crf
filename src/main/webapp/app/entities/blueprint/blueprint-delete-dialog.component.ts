import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IBlueprint } from 'app/shared/model/blueprint.model';
import { BlueprintService } from './blueprint.service';

@Component({
  templateUrl: './blueprint-delete-dialog.component.html',
})
export class BlueprintDeleteDialogComponent {
  blueprint?: IBlueprint;

  constructor(protected blueprintService: BlueprintService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.blueprintService.delete(id).subscribe(() => {
      this.eventManager.broadcast('blueprintListModification');
      this.activeModal.close();
    });
  }
}
