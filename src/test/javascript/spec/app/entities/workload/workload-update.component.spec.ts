import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { CrfTestModule } from '../../../test.module';
import { WorkloadUpdateComponent } from 'app/entities/workload/workload-update.component';
import { WorkloadService } from 'app/entities/workload/workload.service';
import { Workload } from 'app/shared/model/workload.model';

describe('Component Tests', () => {
  describe('Workload Management Update Component', () => {
    let comp: WorkloadUpdateComponent;
    let fixture: ComponentFixture<WorkloadUpdateComponent>;
    let service: WorkloadService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CrfTestModule],
        declarations: [WorkloadUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(WorkloadUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(WorkloadUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(WorkloadService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Workload('123');
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new Workload();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
