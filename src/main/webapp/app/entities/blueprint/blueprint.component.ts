import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IBlueprint } from 'app/shared/model/blueprint.model';
import { BlueprintService } from './blueprint.service';
import { BlueprintDeleteDialogComponent } from './blueprint-delete-dialog.component';

@Component({
  selector: 'jhi-blueprint',
  templateUrl: './blueprint.component.html',
})
export class BlueprintComponent implements OnInit, OnDestroy {
  blueprints?: IBlueprint[];
  eventSubscriber?: Subscription;

  constructor(protected blueprintService: BlueprintService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.blueprintService.query().subscribe((res: HttpResponse<IBlueprint[]>) => (this.blueprints = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInBlueprints();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IBlueprint): string {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInBlueprints(): void {
    this.eventSubscriber = this.eventManager.subscribe('blueprintListModification', () => this.loadAll());
  }

  delete(blueprint: IBlueprint): void {
    const modalRef = this.modalService.open(BlueprintDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.blueprint = blueprint;
  }
}
