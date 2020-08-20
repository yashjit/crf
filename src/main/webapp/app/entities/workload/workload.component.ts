import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IWorkload } from 'app/shared/model/workload.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { WorkloadService } from './workload.service';
import { WorkloadDeleteDialogComponent } from './workload-delete-dialog.component';

@Component({
  selector: 'jhi-workload',
  templateUrl: './workload.component.html',
})
export class WorkloadComponent implements OnInit, OnDestroy {
  workloads: IWorkload[];
  eventSubscriber?: Subscription;
  itemsPerPage: number;
  links: any;
  page: number;
  predicate: string;
  ascending: boolean;

  constructor(
    protected workloadService: WorkloadService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal,
    protected parseLinks: JhiParseLinks
  ) {
    this.workloads = [];
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.page = 0;
    this.links = {
      last: 0,
    };
    this.predicate = 'id';
    this.ascending = true;
  }

  loadAll(): void {
    this.workloadService
      .query({
        page: this.page,
        size: this.itemsPerPage,
        sort: this.sort(),
      })
      .subscribe((res: HttpResponse<IWorkload[]>) => this.paginateWorkloads(res.body, res.headers));
  }

  reset(): void {
    this.page = 0;
    this.workloads = [];
    this.loadAll();
  }

  loadPage(page: number): void {
    this.page = page;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInWorkloads();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IWorkload): string {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInWorkloads(): void {
    this.eventSubscriber = this.eventManager.subscribe('workloadListModification', () => this.reset());
  }

  delete(workload: IWorkload): void {
    const modalRef = this.modalService.open(WorkloadDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.workload = workload;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateWorkloads(data: IWorkload[] | null, headers: HttpHeaders): void {
    const headersLink = headers.get('link');
    this.links = this.parseLinks.parse(headersLink ? headersLink : '');
    if (data) {
      for (let i = 0; i < data.length; i++) {
        this.workloads.push(data[i]);
      }
    }
  }
}
