import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IFieldMaster } from 'app/shared/model/field-master.model';

@Component({
  selector: 'jhi-field-master-detail',
  templateUrl: './field-master-detail.component.html',
})
export class FieldMasterDetailComponent implements OnInit {
  fieldMaster: IFieldMaster | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ fieldMaster }) => (this.fieldMaster = fieldMaster));
  }

  previousState(): void {
    window.history.back();
  }
}
