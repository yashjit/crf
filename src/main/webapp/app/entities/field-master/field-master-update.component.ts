import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IFieldMaster, FieldMaster } from 'app/shared/model/field-master.model';
import { FieldMasterService } from './field-master.service';

@Component({
  selector: 'jhi-field-master-update',
  templateUrl: './field-master-update.component.html',
})
export class FieldMasterUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.required]],
    value: [],
    type: [],
    description: [],
    isRequired: [],
    custom: [],
  });

  constructor(protected fieldMasterService: FieldMasterService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ fieldMaster }) => {
      this.updateForm(fieldMaster);
    });
  }

  updateForm(fieldMaster: IFieldMaster): void {
    this.editForm.patchValue({
      id: fieldMaster.id,
      name: fieldMaster.name,
      value: fieldMaster.value,
      type: fieldMaster.type,
      description: fieldMaster.description,
      isRequired: fieldMaster.isRequired,
      custom: fieldMaster.custom,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const fieldMaster = this.createFromForm();
    if (fieldMaster.id !== undefined) {
      this.subscribeToSaveResponse(this.fieldMasterService.update(fieldMaster));
    } else {
      this.subscribeToSaveResponse(this.fieldMasterService.create(fieldMaster));
    }
  }

  private createFromForm(): IFieldMaster {
    return {
      ...new FieldMaster(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      value: this.editForm.get(['value'])!.value,
      type: this.editForm.get(['type'])!.value,
      description: this.editForm.get(['description'])!.value,
      isRequired: this.editForm.get(['isRequired'])!.value,
      custom: this.editForm.get(['custom'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFieldMaster>>): void {
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
