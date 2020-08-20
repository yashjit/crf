import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IConfigMaster } from 'app/shared/model/config-master.model';
import { ConfigMasterService } from './config-master.service';
import { ConfigMasterDeleteDialogComponent } from './config-master-delete-dialog.component';

@Component({
  selector: 'jhi-config-master',
  templateUrl: './config-master.component.html',
})
export class ConfigMasterComponent implements OnInit, OnDestroy {
  configMasters?: IConfigMaster[];
  eventSubscriber?: Subscription;

  constructor(
    protected configMasterService: ConfigMasterService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.configMasterService.query().subscribe((res: HttpResponse<IConfigMaster[]>) => (this.configMasters = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInConfigMasters();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IConfigMaster): string {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInConfigMasters(): void {
    this.eventSubscriber = this.eventManager.subscribe('configMasterListModification', () => this.loadAll());
  }

  delete(configMaster: IConfigMaster): void {
    const modalRef = this.modalService.open(ConfigMasterDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.configMaster = configMaster;
  }
}
