import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IBlueprint } from 'app/shared/model/blueprint.model';

@Component({
  selector: 'jhi-blueprint-detail',
  templateUrl: './blueprint-detail.component.html',
})
export class BlueprintDetailComponent implements OnInit {
  blueprint: IBlueprint | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ blueprint }) => (this.blueprint = blueprint));
  }

  previousState(): void {
    window.history.back();
  }
}
