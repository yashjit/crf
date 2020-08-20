import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IFieldMaster } from 'app/shared/model/field-master.model';
import { FieldMasterService } from './field-master.service';
import { FieldMasterDeleteDialogComponent } from './field-master-delete-dialog.component';

@Component({
  selector: 'jhi-field-master',
  templateUrl: './field-master.component.html',
})
export class FieldMasterComponent implements OnInit, OnDestroy {
  fieldMasters?: IFieldMaster[];
  eventSubscriber?: Subscription;

  constructor(
    protected fieldMasterService: FieldMasterService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.fieldMasterService.query().subscribe((res: HttpResponse<IFieldMaster[]>) => (this.fieldMasters = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInFieldMasters();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IFieldMaster): string {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInFieldMasters(): void {
    this.eventSubscriber = this.eventManager.subscribe('fieldMasterListModification', () => this.loadAll());
  }

  delete(fieldMaster: IFieldMaster): void {
    const modalRef = this.modalService.open(FieldMasterDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.fieldMaster = fieldMaster;
  }
}
