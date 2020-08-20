import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IWorkload } from 'app/shared/model/workload.model';

@Component({
  selector: 'jhi-workload-detail',
  templateUrl: './workload-detail.component.html',
})
export class WorkloadDetailComponent implements OnInit {
  workload: IWorkload | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ workload }) => (this.workload = workload));
  }

  previousState(): void {
    window.history.back();
  }
}
