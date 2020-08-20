import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IApplication, Application } from 'app/shared/model/application.model';
import { ApplicationService } from './application.service';
import { IWorkload } from 'app/shared/model/workload.model';
import { WorkloadService } from 'app/entities/workload/workload.service';
import { IWave } from 'app/shared/model/wave.model';
import { WaveService } from 'app/entities/wave/wave.service';

type SelectableEntity = IWorkload | IWave;

@Component({
  selector: 'jhi-application-update',
  templateUrl: './application-update.component.html',
})
export class ApplicationUpdateComponent implements OnInit {
  isSaving = false;
  workloads: IWorkload[] = [];
  waves: IWave[] = [];

  editForm = this.fb.group({
    id: [],
    appName: [null, [Validators.required]],
    appType: [null, [Validators.required]],
    custom: [],
    workloads: [],
    waveId: [],
  });

  constructor(
    protected applicationService: ApplicationService,
    protected workloadService: WorkloadService,
    protected waveService: WaveService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ application }) => {
      this.updateForm(application);

      this.workloadService.query().subscribe((res: HttpResponse<IWorkload[]>) => (this.workloads = res.body || []));

      this.waveService.query().subscribe((res: HttpResponse<IWave[]>) => (this.waves = res.body || []));
    });
  }

  updateForm(application: IApplication): void {
    this.editForm.patchValue({
      id: application.id,
      appName: application.appName,
      appType: application.appType,
      custom: application.custom,
      workloads: application.workloads,
      waveId: application.waveId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const application = this.createFromForm();
    if (application.id !== undefined) {
      this.subscribeToSaveResponse(this.applicationService.update(application));
    } else {
      this.subscribeToSaveResponse(this.applicationService.create(application));
    }
  }

  private createFromForm(): IApplication {
    return {
      ...new Application(),
      id: this.editForm.get(['id'])!.value,
      appName: this.editForm.get(['appName'])!.value,
      appType: this.editForm.get(['appType'])!.value,
      custom: this.editForm.get(['custom'])!.value,
      workloads: this.editForm.get(['workloads'])!.value,
      waveId: this.editForm.get(['waveId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IApplication>>): void {
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

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }

  getSelected(selectedVals: IWorkload[], option: IWorkload): IWorkload {
    if (selectedVals) {
      for (let i = 0; i < selectedVals.length; i++) {
        if (option.id === selectedVals[i].id) {
          return selectedVals[i];
        }
      }
    }
    return option;
  }
}
