import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IWorkload, Workload } from 'app/shared/model/workload.model';
import { WorkloadService } from './workload.service';

@Component({
  selector: 'jhi-workload-update',
  templateUrl: './workload-update.component.html',
})
export class WorkloadUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    title: [null, [Validators.required]],
    type: [],
    movegroup: [],
    serverName: [],
    serverTier: [],
    os: [],
    cloudInfo: [],
    description: [],
    custom: [],
    status: [],
    createDate: [],
    modifyDate: [],
    createdBy: [],
  });

  constructor(protected workloadService: WorkloadService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ workload }) => {
      if (!workload.id) {
        const today = moment().startOf('day');
        workload.createDate = today;
        workload.modifyDate = today;
      }

      this.updateForm(workload);
    });
  }

  updateForm(workload: IWorkload): void {
    this.editForm.patchValue({
      id: workload.id,
      title: workload.title,
      type: workload.type,
      movegroup: workload.movegroup,
      serverName: workload.serverName,
      serverTier: workload.serverTier,
      os: workload.os,
      cloudInfo: workload.cloudInfo,
      description: workload.description,
      custom: workload.custom,
      status: workload.status,
      createDate: workload.createDate ? workload.createDate.format(DATE_TIME_FORMAT) : null,
      modifyDate: workload.modifyDate ? workload.modifyDate.format(DATE_TIME_FORMAT) : null,
      createdBy: workload.createdBy,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const workload = this.createFromForm();
    if (workload.id !== undefined) {
      this.subscribeToSaveResponse(this.workloadService.update(workload));
    } else {
      this.subscribeToSaveResponse(this.workloadService.create(workload));
    }
  }

  private createFromForm(): IWorkload {
    return {
      ...new Workload(),
      id: this.editForm.get(['id'])!.value,
      title: this.editForm.get(['title'])!.value,
      type: this.editForm.get(['type'])!.value,
      movegroup: this.editForm.get(['movegroup'])!.value,
      serverName: this.editForm.get(['serverName'])!.value,
      serverTier: this.editForm.get(['serverTier'])!.value,
      os: this.editForm.get(['os'])!.value,
      cloudInfo: this.editForm.get(['cloudInfo'])!.value,
      description: this.editForm.get(['description'])!.value,
      custom: this.editForm.get(['custom'])!.value,
      status: this.editForm.get(['status'])!.value,
      createDate: this.editForm.get(['createDate'])!.value ? moment(this.editForm.get(['createDate'])!.value, DATE_TIME_FORMAT) : undefined,
      modifyDate: this.editForm.get(['modifyDate'])!.value ? moment(this.editForm.get(['modifyDate'])!.value, DATE_TIME_FORMAT) : undefined,
      createdBy: this.editForm.get(['createdBy'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IWorkload>>): void {
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
}
