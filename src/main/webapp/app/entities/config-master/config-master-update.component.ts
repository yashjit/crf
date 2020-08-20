import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IConfigMaster, ConfigMaster } from 'app/shared/model/config-master.model';
import { ConfigMasterService } from './config-master.service';
import { IOrganization } from 'app/shared/model/organization.model';
import { OrganizationService } from 'app/entities/organization/organization.service';

@Component({
  selector: 'jhi-config-master-update',
  templateUrl: './config-master-update.component.html',
})
export class ConfigMasterUpdateComponent implements OnInit {
  isSaving = false;
  organizations: IOrganization[] = [];

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.required]],
    value: [],
    type: [],
    description: [],
    isRequired: [],
    custom: [],
    organizationId: [],
  });

  constructor(
    protected configMasterService: ConfigMasterService,
    protected organizationService: OrganizationService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ configMaster }) => {
      this.updateForm(configMaster);

      this.organizationService.query().subscribe((res: HttpResponse<IOrganization[]>) => (this.organizations = res.body || []));
    });
  }

  updateForm(configMaster: IConfigMaster): void {
    this.editForm.patchValue({
      id: configMaster.id,
      name: configMaster.name,
      value: configMaster.value,
      type: configMaster.type,
      description: configMaster.description,
      isRequired: configMaster.isRequired,
      custom: configMaster.custom,
      organizationId: configMaster.organizationId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const configMaster = this.createFromForm();
    if (configMaster.id !== undefined) {
      this.subscribeToSaveResponse(this.configMasterService.update(configMaster));
    } else {
      this.subscribeToSaveResponse(this.configMasterService.create(configMaster));
    }
  }

  private createFromForm(): IConfigMaster {
    return {
      ...new ConfigMaster(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      value: this.editForm.get(['value'])!.value,
      type: this.editForm.get(['type'])!.value,
      description: this.editForm.get(['description'])!.value,
      isRequired: this.editForm.get(['isRequired'])!.value,
      custom: this.editForm.get(['custom'])!.value,
      organizationId: this.editForm.get(['organizationId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IConfigMaster>>): void {
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

  trackById(index: number, item: IOrganization): any {
    return item.id;
  }
}
