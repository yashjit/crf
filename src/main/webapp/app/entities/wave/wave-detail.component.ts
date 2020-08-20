import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IWave } from 'app/shared/model/wave.model';

@Component({
  selector: 'jhi-wave-detail',
  templateUrl: './wave-detail.component.html',
})
export class WaveDetailComponent implements OnInit {
  wave: IWave | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ wave }) => (this.wave = wave));
  }

  previousState(): void {
    window.history.back();
  }
}
