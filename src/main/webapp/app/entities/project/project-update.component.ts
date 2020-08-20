import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IProject, Project } from 'app/shared/model/project.model';
import { ProjectService } from './project.service';
import { IOrganization } from 'app/shared/model/organization.model';
import { OrganizationService } from 'app/entities/organization/organization.service';

@Component({
  selector: 'jhi-project-update',
  templateUrl: './project-update.component.html',
})
export class ProjectUpdateComponent implements OnInit {
  isSaving = false;
  organizations: IOrganization[] = [];
  startDateDp: any;
  endDateDp: any;

  editForm = this.fb.group({
    id: [],
    projectName: [null, [Validators.required, Validators.maxLength(50)]],
    projectType: [null, [Validators.required]],
    targetCloud: [null, [Validators.required]],
    startDate: [null, [Validators.required]],
    endDate: [null, [Validators.required]],
    custom: [],
    status: [],
    createdBy: [],
    createDate: [],
    modifyDate: [],
    organizationId: [],
  });

  constructor(
    protected projectService: ProjectService,
    protected organizationService: OrganizationService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ project }) => {
      if (!project.id) {
        const today = moment().startOf('day');
        project.createDate = today;
        project.modifyDate = today;
      }

      this.updateForm(project);

      this.organizationService.query().subscribe((res: HttpResponse<IOrganization[]>) => (this.organizations = res.body || []));
    });
  }

  updateForm(project: IProject): void {
    this.editForm.patchValue({
      id: project.id,
      projectName: project.projectName,
      projectType: project.projectType,
      targetCloud: project.targetCloud,
      startDate: project.startDate,
      endDate: project.endDate,
      custom: project.custom,
      status: project.status,
      createdBy: project.createdBy,
      createDate: project.createDate ? project.createDate.format(DATE_TIME_FORMAT) : null,
      modifyDate: project.modifyDate ? project.modifyDate.format(DATE_TIME_FORMAT) : null,
      organizationId: project.organizationId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const project = this.createFromForm();
    if (project.id !== undefined) {
      this.subscribeToSaveResponse(this.projectService.update(project));
    } else {
      this.subscribeToSaveResponse(this.projectService.create(project));
    }
  }

  private createFromForm(): IProject {
    return {
      ...new Project(),
      id: this.editForm.get(['id'])!.value,
      projectName: this.editForm.get(['projectName'])!.value,
      projectType: this.editForm.get(['projectType'])!.value,
      targetCloud: this.editForm.get(['targetCloud'])!.value,
      startDate: this.editForm.get(['startDate'])!.value,
      endDate: this.editForm.get(['endDate'])!.value,
      custom: this.editForm.get(['custom'])!.value,
      status: this.editForm.get(['status'])!.value,
      createdBy: this.editForm.get(['createdBy'])!.value,
      createDate: this.editForm.get(['createDate'])!.value ? moment(this.editForm.get(['createDate'])!.value, DATE_TIME_FORMAT) : undefined,
      modifyDate: this.editForm.get(['modifyDate'])!.value ? moment(this.editForm.get(['modifyDate'])!.value, DATE_TIME_FORMAT) : undefined,
      organizationId: this.editForm.get(['organizationId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IProject>>): void {
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
