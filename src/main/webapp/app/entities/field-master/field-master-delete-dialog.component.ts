import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IFieldMaster } from 'app/shared/model/field-master.model';
import { FieldMasterService } from './field-master.service';

@Component({
  templateUrl: './field-master-delete-dialog.component.html',
})
export class FieldMasterDeleteDialogComponent {
  fieldMaster?: IFieldMaster;

  constructor(
    protected fieldMasterService: FieldMasterService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.fieldMasterService.delete(id).subscribe(() => {
      this.eventManager.broadcast('fieldMasterListModification');
      this.activeModal.close();
    });
  }
}
