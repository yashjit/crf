import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IBlueprint, Blueprint } from 'app/shared/model/blueprint.model';
import { BlueprintService } from './blueprint.service';
import { IWorkload } from 'app/shared/model/workload.model';
import { WorkloadService } from 'app/entities/workload/workload.service';

@Component({
  selector: 'jhi-blueprint-update',
  templateUrl: './blueprint-update.component.html',
})
export class BlueprintUpdateComponent implements OnInit {
  isSaving = false;
  workloads: IWorkload[] = [];

  editForm = this.fb.group({
    id: [],
    title: [null, [Validators.required]],
    type: [],
    description: [],
    template: [],
    custom: [],
    workloadId: [],
  });

  constructor(
    protected blueprintService: BlueprintService,
    protected workloadService: WorkloadService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ blueprint }) => {
      this.updateForm(blueprint);

      this.workloadService.query().subscribe((res: HttpResponse<IWorkload[]>) => (this.workloads = res.body || []));
    });
  }

  updateForm(blueprint: IBlueprint): void {
    this.editForm.patchValue({
      id: blueprint.id,
      title: blueprint.title,
      type: blueprint.type,
      description: blueprint.description,
      template: blueprint.template,
      custom: blueprint.custom,
      workloadId: blueprint.workloadId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const blueprint = this.createFromForm();
    if (blueprint.id !== undefined) {
      this.subscribeToSaveResponse(this.blueprintService.update(blueprint));
    } else {
      this.subscribeToSaveResponse(this.blueprintService.create(blueprint));
    }
  }

  private createFromForm(): IBlueprint {
    return {
      ...new Blueprint(),
      id: this.editForm.get(['id'])!.value,
      title: this.editForm.get(['title'])!.value,
      type: this.editForm.get(['type'])!.value,
      description: this.editForm.get(['description'])!.value,
      template: this.editForm.get(['template'])!.value,
      custom: this.editForm.get(['custom'])!.value,
      workloadId: this.editForm.get(['workloadId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBlueprint>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: IWorkload): any {
    return item.id;
  }
}
