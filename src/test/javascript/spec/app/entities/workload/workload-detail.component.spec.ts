import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CrfTestModule } from '../../../test.module';
import { WorkloadDetailComponent } from 'app/entities/workload/workload-detail.component';
import { Workload } from 'app/shared/model/workload.model';

describe('Component Tests', () => {
  describe('Workload Management Detail Component', () => {
    let comp: WorkloadDetailComponent;
    let fixture: ComponentFixture<WorkloadDetailComponent>;
    const route = ({ data: of({ workload: new Workload('123') }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CrfTestModule],
        declarations: [WorkloadDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(WorkloadDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(WorkloadDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load workload on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.workload).toEqual(jasmine.objectContaining({ id: '123' }));
      });
    });
  });
});
