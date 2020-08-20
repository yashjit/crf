import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IWave, Wave } from 'app/shared/model/wave.model';
import { WaveService } from './wave.service';
import { IProject } from 'app/shared/model/project.model';
import { ProjectService } from 'app/entities/project/project.service';

@Component({
  selector: 'jhi-wave-update',
  templateUrl: './wave-update.component.html',
})
export class WaveUpdateComponent implements OnInit {
  isSaving = false;
  projects: IProject[] = [];

  editForm = this.fb.group({
    id: [],
    waveName: [null, [Validators.required]],
    fileName: [],
    startDate: [],
    endDate: [],
    status: [],
    custom: [],
    createdBy: [],
    createDate: [],
    modifyDate: [],
    projectId: [],
  });

  constructor(
    protected waveService: WaveService,
    protected projectService: ProjectService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ wave }) => {
      if (!wave.id) {
        const today = moment().startOf('day');
        wave.startDate = today;
        wave.endDate = today;
        wave.createDate = today;
        wave.modifyDate = today;
      }

      this.updateForm(wave);

      this.projectService.query().subscribe((res: HttpResponse<IProject[]>) => (this.projects = res.body || []));
    });
  }

  updateForm(wave: IWave): void {
    this.editForm.patchValue({
      id: wave.id,
      waveName: wave.waveName,
      fileName: wave.fileName,
      startDate: wave.startDate ? wave.startDate.format(DATE_TIME_FORMAT) : null,
      endDate: wave.endDate ? wave.endDate.format(DATE_TIME_FORMAT) : null,
      status: wave.status,
      custom: wave.custom,
      createdBy: wave.createdBy,
      createDate: wave.createDate ? wave.createDate.format(DATE_TIME_FORMAT) : null,
      modifyDate: wave.modifyDate ? wave.modifyDate.format(DATE_TIME_FORMAT) : null,
      projectId: wave.projectId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const wave = this.createFromForm();
    if (wave.id !== undefined) {
      this.subscribeToSaveResponse(this.waveService.update(wave));
    } else {
      this.subscribeToSaveResponse(this.waveService.create(wave));
    }
  }

  private createFromForm(): IWave {
    return {
      ...new Wave(),
      id: this.editForm.get(['id'])!.value,
      waveName: this.editForm.get(['waveName'])!.value,
      fileName: this.editForm.get(['fileName'])!.value,
      startDate: this.editForm.get(['startDate'])!.value ? moment(this.editForm.get(['startDate'])!.value, DATE_TIME_FORMAT) : undefined,
      endDate: this.editForm.get(['endDate'])!.value ? moment(this.editForm.get(['endDate'])!.value, DATE_TIME_FORMAT) : undefined,
      status: this.editForm.get(['status'])!.value,
      custom: this.editForm.get(['custom'])!.value,
      createdBy: this.editForm.get(['createdBy'])!.value,
      createDate: this.editForm.get(['createDate'])!.value ? moment(this.editForm.get(['createDate'])!.value, DATE_TIME_FORMAT) : undefined,
      modifyDate: this.editForm.get(['modifyDate'])!.value ? moment(this.editForm.get(['modifyDate'])!.value, DATE_TIME_FORMAT) : undefined,
      projectId: this.editForm.get(['projectId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IWave>>): void {
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

  trackById(index: number, item: IProject): any {
    return item.id;
  }
}
