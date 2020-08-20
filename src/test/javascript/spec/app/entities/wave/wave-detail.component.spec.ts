import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CrfTestModule } from '../../../test.module';
import { WaveDetailComponent } from 'app/entities/wave/wave-detail.component';
import { Wave } from 'app/shared/model/wave.model';

describe('Component Tests', () => {
  describe('Wave Management Detail Component', () => {
    let comp: WaveDetailComponent;
    let fixture: ComponentFixture<WaveDetailComponent>;
    const route = ({ data: of({ wave: new Wave('123') }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CrfTestModule],
        declarations: [WaveDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(WaveDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(WaveDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load wave on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.wave).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
